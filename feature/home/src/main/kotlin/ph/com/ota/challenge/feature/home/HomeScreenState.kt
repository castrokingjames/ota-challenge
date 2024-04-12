/*
* Copyright 2024
*/
package ph.com.ota.challenge.feature.home

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState

data class HomeScreenState(
  @PersistState
  val name: String,
) : MavericksState {

  constructor(args: HomeScreenArgs) : this(args.name)
}
