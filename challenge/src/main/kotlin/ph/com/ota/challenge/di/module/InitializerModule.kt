/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton
import ph.com.ota.challenge.initializer.Initializer
import ph.com.ota.challenge.initializer.MavericksInitializer
import ph.com.ota.challenge.initializer.StartupInitializer
import ph.com.ota.challenge.initializer.TimberInitializer

@Module
abstract class InitializerModule {

  @Binds
  @IntoSet
  abstract fun bindsMavericksInitializer(initializer: MavericksInitializer): Initializer

  @Binds
  @IntoSet
  abstract fun bindsTimberInitializer(initializer: TimberInitializer): Initializer

  companion object {

    @Provides
    @Singleton
    fun providesStartupInitializer(initializer: StartupInitializer): Initializer {
      return initializer
    }
  }
}
