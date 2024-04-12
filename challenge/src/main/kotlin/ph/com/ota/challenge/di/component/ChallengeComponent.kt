/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import ph.com.ota.challenge.ChallengeApplication
import ph.com.ota.challenge.di.module.ApplicationModule
import ph.com.ota.challenge.di.module.ConfigModule
import ph.com.ota.challenge.di.module.DispatchersModule
import ph.com.ota.challenge.di.module.GsonModule
import ph.com.ota.challenge.di.module.InitializerModule
import ph.com.ota.challenge.di.module.ViewManagerModule
import ph.com.ota.challenge.di.module.data.ActivityModule
import ph.com.ota.challenge.di.module.data.LevelModule
import ph.com.ota.challenge.di.module.data.local.DatabaseModule
import ph.com.ota.challenge.di.module.data.remote.NetworkModule

@Singleton
@Component(
  modules = [
    ApplicationModule::class,
    DispatchersModule::class,
    InitializerModule::class,
    ConfigModule::class,
    GsonModule::class,
    ViewManagerModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    LevelModule::class,
    ActivityModule::class,
  ],
)
interface ChallengeComponent {

  fun inject(application: ChallengeApplication)

  @Component.Factory
  interface Factory {
    fun build(
      @BindsInstance application: Application,
    ): ChallengeComponent
  }
}
