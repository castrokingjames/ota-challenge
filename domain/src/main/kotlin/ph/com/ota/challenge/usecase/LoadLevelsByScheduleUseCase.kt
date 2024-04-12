/*
* Copyright 2024
*/
package ph.com.ota.challenge.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import ph.com.ota.challenge.model.Level
import ph.com.ota.challenge.repository.LevelRepository

class LoadLevelsByScheduleUseCase @Inject constructor(
  private val levelRepository: LevelRepository,
) {

  operator fun invoke(schedule: String): Flow<List<Level>> {
    return channelFlow {
      levelRepository
        .loadBySchedule(schedule)
        .collectLatest { levels ->
          send(levels)
        }
    }
  }
}
