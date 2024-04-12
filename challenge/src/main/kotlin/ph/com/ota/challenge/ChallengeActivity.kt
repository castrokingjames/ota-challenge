/*
* Copyright 2024
*/
package ph.com.ota.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ph.com.ota.challenge.ui.ChallengeApp
import ph.com.ota.challenge.ui.compose.theme.ChallengeTheme

class ChallengeActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ChallengeTheme {
        ChallengeApp()
      }
    }
  }
}
