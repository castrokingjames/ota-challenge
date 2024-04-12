/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data.local

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ph.com.ota.challenge.datasource.local.database.Database

@Module(
  includes = [
    QueryModule::class,
  ],
)
class DatabaseModule {

  @Provides
  @Singleton
  fun providesSqlDriver(context: Context): SqlDriver {
    return AndroidSqliteDriver(
      Database.Schema,
      context,
      null,
      callback = object : AndroidSqliteDriver.Callback(Database.Schema) {
        override fun onConfigure(db: SupportSQLiteDatabase) {
          db.setForeignKeyConstraintsEnabled(true)
        }
      },
    )
  }

  @Provides
  @Singleton
  fun providesDatabase(driver: SqlDriver): Database {
    return Database(driver)
  }
}
