/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data.remote

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ph.com.ota.challenge.datasource.remote.service.LevelService
import retrofit2.Retrofit

@Module
class ServiceModule {

  @Provides
  @Singleton
  fun providesLevelService(retrofit: Retrofit): LevelService {
    return retrofit.create(LevelService::class.java)
  }
}
