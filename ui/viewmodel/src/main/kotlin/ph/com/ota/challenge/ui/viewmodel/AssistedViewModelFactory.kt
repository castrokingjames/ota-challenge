/*
* Copyright 2022
*/
package ph.com.ota.challenge.ui.viewmodel

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

interface AssistedViewModelFactory<VM : MavericksViewModel<S>, S : MavericksState> {

  fun create(state: S): VM
}
