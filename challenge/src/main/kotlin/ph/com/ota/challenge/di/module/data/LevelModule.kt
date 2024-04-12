/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ph.com.ota.challenge.data.LevelDataRepository
import ph.com.ota.challenge.repository.LevelRepository

@Module
class LevelModule {

  @Provides
  @Singleton
  fun providesLevelRepository(repository: LevelDataRepository): LevelRepository {
    return repository
  }
}
