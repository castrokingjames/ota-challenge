/*
* Copyright 2024
*/
package ph.com.ota.challenge.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import ph.com.ota.challenge.datasource.local.database.ActivitiesQueries
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread
import ph.com.ota.challenge.model.Activity
import ph.com.ota.challenge.repository.ActivityRepository

class ActivityDataRepository @Inject constructor(
  private val activitiesQueries: ActivitiesQueries,
  @Dispatcher(Thread.IO)
  private val io: CoroutineDispatcher,
) : ActivityRepository {

  override suspend fun loadByLevelId(levelId: String): Flow<List<Activity>> {
    return channelFlow {
      activitiesQueries
        .selectAllByLevelId(levelId)
        .asFlow()
        .mapToList(io)
        .map { activities ->
          activities.map { activities ->
            val id: String = activities.id
            val type: String = activities.type
            val title: String = activities.title
            val description: String = activities.description
            val state: String = activities.state
            val active: String = activities.activeIcon
            val inactive: String = activities.inactiveIcon
            Activity(
              id,
              type,
              title,
              description,
              state,
              Activity.Icon(active, inactive),
            )
          }
        }
        .collectLatest { activities ->
          send(activities)
        }
    }
  }

  override suspend fun loadByActivityId(activityId: String): Flow<Activity> {
    return channelFlow {
      activitiesQueries
        .selectByActivityId(activityId)
        .asFlow()
        .mapToOne(io)
        .map { activities ->
          val id: String = activities.id
          val type: String = activities.type
          val title: String = activities.title
          val description: String = activities.description
          val state: String = activities.state
          val active: String = activities.activeIcon
          val inactive: String = activities.inactiveIcon
          Activity(
            id,
            type,
            title,
            description,
            state,
            Activity.Icon(active, inactive),
          )
        }
        .collectLatest { activities ->
          send(activities)
        }
    }
  }
}
