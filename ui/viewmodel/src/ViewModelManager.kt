/*
* Copyright 2022
*/
package com.fifteenfive.fifteenfiveapp.ui.viewmodel

import com.airbnb.mvrx.MavericksViewModel

interface ViewModelManager {

  fun get(key: Class<out MavericksViewModel<*>>): AssistedViewModelFactory<*, *>?
}
