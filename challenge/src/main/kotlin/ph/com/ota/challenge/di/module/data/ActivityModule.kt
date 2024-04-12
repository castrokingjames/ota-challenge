/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ph.com.ota.challenge.data.ActivityDataRepository
import ph.com.ota.challenge.repository.ActivityRepository

@Module
class ActivityModule {

  @Provides
  @Singleton
  fun providesActivityRepository(repository: ActivityDataRepository): ActivityRepository {
    return repository
  }
}
