/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module

import com.airbnb.mvrx.MavericksViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ph.com.ota.challenge.ui.viewmodel.AssistedViewModelFactory
import ph.com.ota.challenge.ui.viewmodel.SimpleViewModelManager
import ph.com.ota.challenge.ui.viewmodel.ViewModelManager

@Module(
  includes = [
    ViewModelModule::class,
  ],
)
class ViewManagerModule {

  @Provides
  @Singleton
  fun providesViewModelManager(map: Map<Class<out MavericksViewModel<*>>, @JvmSuppressWildcards AssistedViewModelFactory<*, *>>): ViewModelManager {
    return SimpleViewModelManager(map)
  }
}
