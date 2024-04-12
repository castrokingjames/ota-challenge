/*
* Copyright 2022
*/
package com.fifteenfive.fifteenfiveapp.ui.viewmodel

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.fifteenfive.fifteenfiveapp.manager.DispatcherManager
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

abstract class ViewModel<S : MavericksState> constructor(
  private val initialState: S,
  private val dispatcherManager: DispatcherManager,
) : MavericksViewModel<S>(initialState), CoroutineScope {

  private val job = SupervisorJob()

  override val coroutineContext: CoroutineContext get() = dispatcherManager.ui + job

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}
