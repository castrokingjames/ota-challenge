/*
* Copyright 2024
*/
package ph.com.ota.challenge.di.module.data.remote

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(
  includes = [
    ServiceModule::class,
    InterceptorModule::class,
  ],
)
class NetworkModule {

  @Provides
  @Singleton
  fun providesConverterFactory(gson: Gson): Converter.Factory {
    return GsonConverterFactory.create(gson)
  }

  @Provides
  @Singleton
  fun providesOkHttpClient(interceptors: Map<Int, @JvmSuppressWildcards Interceptor>): OkHttpClient {
    var builder = OkHttpClient
      .Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
    interceptors.toSortedMap().forEach { entry ->
      val interceptor = entry.value
      builder.addInterceptor(interceptor)
    }

    return builder.build()
  }

  @Provides
  @Singleton
  fun providesRetrofit(
    @Named("BASE_URL") baseUrl: String,
    okHttpClient: OkHttpClient,
    converterFactory: Converter.Factory,
  ): Retrofit {
    return Retrofit
      .Builder()
      .baseUrl(baseUrl)
      .client(okHttpClient)
      .addConverterFactory(converterFactory)
      .build()
  }
}
