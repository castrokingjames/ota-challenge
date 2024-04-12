/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Named
import ph.com.ota.challenge.BuildConfig

@Module
class ConfigModule {

  @Provides
  @Named("BASE_URL")
  fun providesShopifyBaseURL(): String {
    return BuildConfig.BASE_URL
  }
}
