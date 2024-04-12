/*
* Copyright 2024
*/
package ph.com.ota.challenge.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class ActivityResponse(
  @SerializedName("id")
  val id: String,
  @SerializedName("type")
  val type: String,
  @SerializedName("title")
  val title: String,
  @SerializedName("description")
  val description: String,
  @SerializedName("state")
  val state: String,
  @SerializedName("icon")
  val active: IconResponse,
  @SerializedName("lockedIcon")
  val inactive: IconResponse,
) {

  data class IconResponse(
    @SerializedName("file")
    val file: FileResponse,
  )

  data class FileResponse(
    @SerializedName("url")
    val url: String,
  )
}
