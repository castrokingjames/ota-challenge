/*
* Copyright 2024
*/
package ph.com.ota.challenge.feature.details

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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread
import ph.com.ota.challenge.ui.viewmodel.AssistedViewModelFactory
import ph.com.ota.challenge.ui.viewmodel.ViewModel
import ph.com.ota.challenge.ui.viewmodel.viewModelFactory
import ph.com.ota.challenge.usecase.LoadActivityByActivityIdUseCase
import timber.log.Timber
import timber.log.error

class DetailsScreenViewModel @AssistedInject constructor(
  @Assisted
  private val initialState: DetailsScreenState,
  private val loadActivityByActivityId: LoadActivityByActivityIdUseCase,
  @Dispatcher(Thread.MAIN)
  private val ui: CoroutineDispatcher,
  @Dispatcher(Thread.IO)
  private val io: CoroutineDispatcher,
) : ViewModel<DetailsScreenState>(initialState, ui) {
  init {
    load()
  }

  private fun load() {
    val context = CoroutineExceptionHandler { _, exception ->
      setState {
        copy(loading = Fail(exception))
      }
      Timber.error { "DetailsScreenViewModel Error: $exception" }
    } + io
    launch(context) {
      setState {
        copy(loading = Loading())
      }
      loadActivityByActivityId(initialState.activityId)
        .collectLatest { activity ->
          setState {
            copy(
              loading = Success(false),
              activity = activity,
            )
          }
        }
    }
  }

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<DetailsScreenViewModel, DetailsScreenState> {
    override fun create(state: DetailsScreenState): DetailsScreenViewModel
  }

  companion object :
    MavericksViewModelFactory<DetailsScreenViewModel, DetailsScreenState> by viewModelFactory() {

    @Composable
    fun detailsScreenViewModel(activityId: String): DetailsScreenViewModel = mavericksViewModel(
      keyFactory = {
        "DetailsScreenViewModel$activityId"
      },
      argsFactory = {
        DetailsScreenArgs(activityId)
      },
    )
  }
}
