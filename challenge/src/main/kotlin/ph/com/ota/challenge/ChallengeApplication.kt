/*
* Copyright 2024
*/
package ph.com.ota.challenge

import android.app.Application
import javax.inject.Inject
import ph.com.ota.challenge.di.component.ChallengeComponent
import ph.com.ota.challenge.di.component.DaggerChallengeComponent
import ph.com.ota.challenge.initializer.Initializer
import ph.com.ota.challenge.ui.viewmodel.HasViewModelManager
import ph.com.ota.challenge.ui.viewmodel.ViewModelManager

class ChallengeApplication : Application(), HasViewModelManager {

  private lateinit var component: ChallengeComponent

  @Inject
  lateinit var initializer: Initializer

  @Inject
  lateinit var viewModelManager: ViewModelManager

  override fun onCreate() {
    super.onCreate()
    component = DaggerChallengeComponent
      .factory()
      .build(this)
    component.inject(this)

    initializer()
  }

  override fun viewModelManager(): ViewModelManager {
    return viewModelManager
  }
}
