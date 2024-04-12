/*
* Copyright 2022
*/
package ph.com.ota.challenge.initializer

import javax.inject.Inject
import ph.com.ota.challenge.BuildConfig
import ph.com.ota.challenge.timber.LogcatTree
import timber.log.Timber

class TimberInitializer @Inject constructor() : Initializer {

  override fun invoke() {
    if (BuildConfig.DEBUG) {
      Timber.plant(LogcatTree())
    }
  }
}
