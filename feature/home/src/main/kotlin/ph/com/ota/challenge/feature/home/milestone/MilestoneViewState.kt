/*
* Copyright 2024
*/
package ph.com.ota.challenge.feature.home.milestone

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized
import ph.com.ota.challenge.model.Activity
import ph.com.ota.challenge.model.Level

data class MilestoneViewState(
  @PersistState
  val id: String,
  @PersistState
  val milestone: Map<Level, List<Activity>>? = null,
  val loading: Async<Boolean> = Uninitialized,
) : MavericksState {

  constructor(args: MilestoneViewArgs) : this(args.id)
}
