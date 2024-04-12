/*
* Copyright 2024
*/
package ph.com.ota.challenge.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import timber.log.error

object LoggingInterceptor : Interceptor {

  @JvmStatic
  var logger: Interceptor = HttpLoggingInterceptor(Logger)
    .apply {
      level = HttpLoggingInterceptor.Level.BASIC
    }

  override fun intercept(chain: Interceptor.Chain): Response {
    return logger.intercept(chain)
  }

  object Logger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
      Timber.error { message }
    }
  }
}
