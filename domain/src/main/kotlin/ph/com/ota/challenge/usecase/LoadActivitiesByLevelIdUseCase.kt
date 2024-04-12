/*
* Copyright 2024
*/
package ph.com.ota.challenge.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import ph.com.ota.challenge.model.Activity
import ph.com.ota.challenge.repository.ActivityRepository

class LoadActivitiesByLevelIdUseCase @Inject constructor(
  private val activityRepository: ActivityRepository,
) {

  operator fun invoke(levelId: String): Flow<List<Activity>> {
    return channelFlow {
      activityRepository
        .loadByLevelId(levelId)
        .collectLatest { activities ->
          send(activities)
        }
    }
  }
}
