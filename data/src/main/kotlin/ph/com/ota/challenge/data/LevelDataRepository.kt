/*
* Copyright 2024
*/
package ph.com.ota.challenge.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ph.com.ota.challenge.datasource.local.database.Activities
import ph.com.ota.challenge.datasource.local.database.ActivitiesQueries
import ph.com.ota.challenge.datasource.local.database.LevelActivities
import ph.com.ota.challenge.datasource.local.database.LevelActivitiesQueries
import ph.com.ota.challenge.datasource.local.database.LevelSchedule
import ph.com.ota.challenge.datasource.local.database.LevelScheduleQueries
import ph.com.ota.challenge.datasource.local.database.Levels
import ph.com.ota.challenge.datasource.local.database.LevelsQueries
import ph.com.ota.challenge.datasource.remote.response.ActivityResponse
import ph.com.ota.challenge.datasource.remote.response.LevelResponse
import ph.com.ota.challenge.datasource.remote.service.LevelService
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread
import ph.com.ota.challenge.model.Level
import ph.com.ota.challenge.repository.LevelRepository

class LevelDataRepository @Inject constructor(
  private val levelService: LevelService,
  private val levelsQueries: LevelsQueries,
  private val activityQueries: ActivitiesQueries,
  private val levelScheduleQueries: LevelScheduleQueries,
  private val levelActivitiesQueries: LevelActivitiesQueries,
  @Dispatcher(Thread.IO)
  private val io: CoroutineDispatcher,
) : LevelRepository {

  override suspend fun loadBySchedule(schedule: String): Flow<List<Level>> {
    return channelFlow {
      launch {
        val levels: List<LevelResponse> = levelService
          .load(schedule)
          .levels
        levels
          .forEachIndexed { index, level ->
            // Add prefix for mock id
            val id: String = level.id(schedule)
            val title: String = level.title
            val description: String = level.description
            val state: String = level.state
            val levels = Levels(id, title, description, state, index + 1L)
            levelsQueries.upsert(levels)

            val levelSchedule = LevelSchedule(id, schedule)
            levelScheduleQueries.upsert(levelSchedule)

            val activities: List<ActivityResponse> = level.activities
            activities.forEachIndexed { index, activity ->
              val id: String = activity.id
              val type: String = activity.type
              val title: String = activity.title
              val description: String = activity.description
              val state: String = activity.state
              val active: String = activity.active.file.url
              val inactive: String = activity.inactive.file.url
              val activities = Activities(
                id,
                type,
                title,
                description,
                state,
                active,
                inactive,
                index + 1L,
              )
              activityQueries.upsert(activities)

              val levelActivities = LevelActivities(level.id(schedule), activity.id)
              levelActivitiesQueries.upsert(levelActivities)
            }
          }
      }

      levelsQueries
        .selectAllBySchedule(schedule)
        .asFlow()
        .mapToList(io)
        .map { levels ->
          levels.map { level ->
            val id: String = level.id
            val title: String = level.title
            val description: String = level.description
            val state: String = level.state
            val ordinal: Int = level.ordinal.toInt()
            Level(id, ordinal, title, description, state)
          }
        }
        .collectLatest { levels ->
          send(levels)
        }
    }
  }
}
