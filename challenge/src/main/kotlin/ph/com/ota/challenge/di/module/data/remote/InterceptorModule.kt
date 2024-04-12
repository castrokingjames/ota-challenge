/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data.remote

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import javax.inject.Singleton
import okhttp3.Interceptor
import ph.com.ota.challenge.datasource.remote.interceptor.LoggingInterceptor

@Module
abstract class InterceptorModule {

  @Multibinds
  abstract fun bindsInterceptors(): Map<Int, @JvmSuppressWildcards Interceptor>

  @Module
  companion object {

    @IntoMap
    @Provides
    @JvmStatic
    @IntKey(1)
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
      return LoggingInterceptor
    }
  }
}
