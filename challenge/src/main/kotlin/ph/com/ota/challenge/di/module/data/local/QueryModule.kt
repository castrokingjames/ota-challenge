/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data.local

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ph.com.ota.challenge.datasource.local.database.ActivitiesQueries
import ph.com.ota.challenge.datasource.local.database.Database
import ph.com.ota.challenge.datasource.local.database.LevelActivitiesQueries
import ph.com.ota.challenge.datasource.local.database.LevelScheduleQueries
import ph.com.ota.challenge.datasource.local.database.LevelsQueries

@Module
class QueryModule {

  @Provides
  @Singleton
  fun providesLevelsQueries(database: Database): LevelsQueries {
    return database.levelsQueries
  }

  @Provides
  @Singleton
  fun providesActivitiesQueries(database: Database): ActivitiesQueries {
    return database.activitiesQueries
  }

  @Provides
  @Singleton
  fun providesLevelActivitiesQueries(database: Database): LevelActivitiesQueries {
    return database.levelActivitiesQueries
  }

  @Provides
  @Singleton
  fun providesLevelScheduleQueries(database: Database): LevelScheduleQueries {
    return database.levelScheduleQueries
  }
}
