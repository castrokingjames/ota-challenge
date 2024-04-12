/*
* Copyright 2024
*/
package ph.com.ota.challenge.repository

import kotlinx.coroutines.flow.Flow
import ph.com.ota.challenge.model.Activity

interface ActivityRepository {

  suspend fun loadByLevelId(levelId: String): Flow<List<Activity>>

  suspend fun loadByActivityId(activityId: String): Flow<Activity>
}
