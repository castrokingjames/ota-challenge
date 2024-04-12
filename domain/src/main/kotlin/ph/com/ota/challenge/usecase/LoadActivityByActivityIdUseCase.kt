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

class LoadActivityByActivityIdUseCase @Inject constructor(
  private val activityRepository: ActivityRepository,
) {

  operator fun invoke(activityId: String): Flow<Activity> {
    return channelFlow {
      activityRepository
        .loadByActivityId(activityId)
        .collectLatest { activity ->
          send(activity)
        }
    }
  }
}
