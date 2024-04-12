/*
* Copyright 2024
*/
@file:OptIn(ExperimentalCoroutinesApi::class)

package ph.com.ota.challenge.feature.home.milestone

import androidx.compose.runtime.Composable
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.mavericksViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread
import ph.com.ota.challenge.ui.viewmodel.AssistedViewModelFactory
import ph.com.ota.challenge.ui.viewmodel.ViewModel
import ph.com.ota.challenge.ui.viewmodel.viewModelFactory
import ph.com.ota.challenge.usecase.LoadActivitiesByLevelIdUseCase
import ph.com.ota.challenge.usecase.LoadLevelsByScheduleUseCase
import timber.log.Timber
import timber.log.error

class MilestoneViewModel @AssistedInject constructor(
  @Assisted
  private val initialState: MilestoneViewState,
  private val loadLevelsById: LoadLevelsByScheduleUseCase,
  private val loadActivitiesByLevelId: LoadActivitiesByLevelIdUseCase,
  @Dispatcher(Thread.MAIN)
  private val ui: CoroutineDispatcher,
  @Dispatcher(Thread.IO)
  private val io: CoroutineDispatcher,
) : ViewModel<MilestoneViewState>(initialState, ui) {

  fun load() {
    val context = CoroutineExceptionHandler { _, exception ->
      setState {
        copy(loading = Fail(exception))
      }
      Timber.error { "MilestoneViewModel Error: $exception" }
    } + io
    launch(context) {
      setState {
        copy(loading = Loading())
      }
      loadLevelsById(initialState.id)
        .flatMapLatest { levels ->
          levels
            .map { level ->
              loadActivitiesByLevelId(level.id)
                .map {
                  Pair(level, it)
                }
            }
            .let { activities ->
              combine(activities) { activities ->
                activities.toMap()
              }
            }
        }
        .collectLatest { milestone ->
          setState {
            copy(
              loading = Success(false),
              milestone = milestone,
            )
          }
        }
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<MilestoneViewModel, MilestoneViewState> {
    override fun create(state: MilestoneViewState): MilestoneViewModel
  }

  companion object :
    MavericksViewModelFactory<MilestoneViewModel, MilestoneViewState> by viewModelFactory() {

    @Composable
    fun milestoneViewModel(id: String): MilestoneViewModel = mavericksViewModel(
      keyFactory = {
        "MilestoneViewModel_$id"
      },
      argsFactory = {
        MilestoneViewArgs(id)
      },
    )
  }
}
