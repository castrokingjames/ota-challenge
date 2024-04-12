/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ph.com.ota.challenge.di.annotation.Dispatcher
import ph.com.ota.challenge.di.annotation.Thread

@Module
class DispatchersModule {

  @Provides
  @Dispatcher(Thread.IO)
  @Singleton
  fun providesIO(): CoroutineDispatcher {
    return Dispatchers.IO
  }

  @Provides
  @Dispatcher(Thread.MAIN)
  @Singleton
  fun providesMain(): CoroutineDispatcher {
    return Dispatchers.Main
  }
}
