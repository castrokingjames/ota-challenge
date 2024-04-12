/*
* Copyright 2022
*/
package com.fifteenfive.fifteenfiveapp.ui.viewmodel

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext

class ViewModelFactory<VM : MavericksViewModel<S>, S : MavericksState> constructor(
  private val viewModelClass: Class<VM>,
) : MavericksViewModelFactory<VM, S> {

  override fun create(viewModelContext: ViewModelContext, state: S): VM {
    val activity = viewModelContext.activity
    val viewModelManager = if (activity is HasViewModelManager) {
      activity.viewModelManager()
    } else {
      throw IllegalArgumentException("${activity.javaClass.simpleName} should inherit HasViewModelManager")
    }
    val viewModelFactory = viewModelManager.get(viewModelClass)

    @Suppress("UNCHECKED_CAST")
    val castedViewModelFactory = viewModelFactory as? AssistedViewModelFactory<VM, S>
    val viewModel = castedViewModelFactory?.create(state)
    return viewModel as VM
  }
}

inline fun <reified VM : MavericksViewModel<S>, S : MavericksState> viewModelFactory() = ViewModelFactory(VM::class.java)
