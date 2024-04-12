/*
* Copyright 2024
*/
package ph.com.ota.challenge.model

data class Activity(
  val id: String,
  val type: String,
  val title: String,
  val description: String,
  val state: String,
  val icon: Icon,
) {

  data class Icon(
    val active: String,
    val inactive: String,
  )
}
