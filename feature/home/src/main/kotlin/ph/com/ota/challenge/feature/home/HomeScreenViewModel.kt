/*
* Copyright 2024
*/
package ph.com.ota.challenge.feature.home

import androidx.compose.runtime.Composable
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.compose.mavericksViewModel
import com.airbnb.mvrx.withState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread
import ph.com.ota.challenge.ui.viewmodel.AssistedViewModelFactory
import ph.com.ota.challenge.ui.viewmodel.ViewModel
import ph.com.ota.challenge.ui.viewmodel.viewModelFactory

class HomeScreenViewModel @AssistedInject constructor(
  @Assisted
  private val initialState: HomeScreenState,
  @Dispatcher(Thread.MAIN)
  private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel<HomeScreenState>(initialState, coroutineDispatcher) {

  val name: String
    get() {
      return withState(this) { state ->
        state.name
      }
    }

  val progress: Float = 39f

  @AssistedFactory
  interface Factory : AssistedViewModelFactory<HomeScreenViewModel, HomeScreenState> {
    override fun create(state: HomeScreenState): HomeScreenViewModel
  }

  companion object :
    MavericksViewModelFactory<HomeScreenViewModel, HomeScreenState> by viewModelFactory() {

    @Composable
    fun homeScreenViewModel(name: String): HomeScreenViewModel = mavericksViewModel(
      keyFactory = {
        "HomeScreenViewModel_$name"
      },
      argsFactory = {
        HomeScreenArgs(name)
      },
    )
  }
}
