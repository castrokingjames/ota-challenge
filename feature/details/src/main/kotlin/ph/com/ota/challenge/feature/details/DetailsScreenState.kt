/*
* Copyright 2024
*/
package ph.com.ota.challenge.feature.details

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized
import ph.com.ota.challenge.model.Activity

data class DetailsScreenState(
  @PersistState
  val activityId: String,
  @PersistState
  val activity: Activity? = null,
  val loading: Async<Boolean> = Uninitialized,
) : MavericksState {

  constructor(args: DetailsScreenArgs) : this(args.activityId)
}
