/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui

const val ACTIVITY_ID = "activity_id"

sealed class Screen(val route: String) {

  data object DetailScreen : Screen("activity/{$ACTIVITY_ID}") {

    fun withId(activityId: String): String {
      return route.replace("{$ACTIVITY_ID}", activityId)
    }
  }

  data object HomeScreen : Screen("home")
}
