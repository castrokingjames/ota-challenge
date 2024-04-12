/*
* Copyright 2024
*/
package ph.com.ota.challenge.datasource.remote.service

import ph.com.ota.challenge.datasource.remote.response.LevelsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LevelService {

  @GET("day/{schedule}")
  suspend fun load(@Path("schedule") schedule: String): LevelsResponse
}
