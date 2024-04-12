/*
* Copyright 2024
*/
package ph.com.ota.challenge.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class LevelResponse(
  @SerializedName("level")
  val id: String,
  @SerializedName("title")
  val title: String,
  @SerializedName("description")
  val description: String,
  @SerializedName("state")
  val state: String,
  @SerializedName("activities")
  val activities: List<ActivityResponse>,
) {

  fun id(schedule: String): String {
    return "$schedule-${this.id}"
  }
}
