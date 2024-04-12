/*
* Copyright 2024
*/
package ph.com.ota.challenge.model

data class Level(
  val id: String,
  val number: Int,
  val title: String,
  val description: String,
  val state: String,
) {

  val active: Boolean
    get() {
      return state == "AVAILABLE"
    }
}
