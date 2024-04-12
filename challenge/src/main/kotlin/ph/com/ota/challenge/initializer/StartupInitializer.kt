/*
* Copyright 2024
*/
package ph.com.ota.challenge.initializer

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread

class StartupInitializer @Inject constructor(
  private val initializers: Set<@JvmSuppressWildcards Initializer>,
  @Dispatcher(Thread.IO) private val dispatcher: CoroutineDispatcher,
) : Initializer {

  override fun invoke() {
    val context = CoroutineScope(dispatcher)
    context.launch {
      initializers.forEach(Initializer::invoke)
    }
  }
}
