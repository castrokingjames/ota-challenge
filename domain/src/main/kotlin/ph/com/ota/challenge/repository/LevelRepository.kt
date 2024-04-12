/*
* Copyright 2024
*/
package ph.com.ota.challenge.repository

import kotlinx.coroutines.flow.Flow
import ph.com.ota.challenge.model.Level

interface LevelRepository {

  suspend fun loadBySchedule(schedule: String): Flow<List<Level>>
}
