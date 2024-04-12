/*
* Copyright 2024
*/
package ph.com.ota.challenge.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class LevelsResponse(
  @SerializedName("levels")
  val levels: List<LevelResponse>,
)
