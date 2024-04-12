/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ph.com.ota.challenge.di.annotation.ViewModelKey
import ph.com.ota.challenge.feature.details.DetailsScreenViewModel
import ph.com.ota.challenge.feature.home.HomeScreenViewModel
import ph.com.ota.challenge.feature.home.milestone.MilestoneViewModel
import ph.com.ota.challenge.ui.viewmodel.AssistedViewModelFactory

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(HomeScreenViewModel::class)
  abstract fun homeScreenViewModelFactory(factory: HomeScreenViewModel.Factory?): AssistedViewModelFactory<*, *>

  @Binds
  @IntoMap
  @ViewModelKey(MilestoneViewModel::class)
  abstract fun milestoneViewModelFactory(factory: MilestoneViewModel.Factory?): AssistedViewModelFactory<*, *>

  @Binds
  @IntoMap
  @ViewModelKey(DetailsScreenViewModel::class)
  abstract fun detailsScreenViewModelFactory(factory: DetailsScreenViewModel.Factory?): AssistedViewModelFactory<*, *>
}
