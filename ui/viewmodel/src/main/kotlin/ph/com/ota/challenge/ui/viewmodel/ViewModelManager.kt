/*
* Copyright 2022
*/
package ph.com.ota.challenge.ui.viewmodel

import com.airbnb.mvrx.MavericksViewModel

interface ViewModelManager {

  fun get(key: Class<out MavericksViewModel<*>>): AssistedViewModelFactory<*, *>?
}
