/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui.compose.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import ph.com.ota.challenge.ui.compose.R

object Font {

  val euclidCircularBRegular = FontFamily(
    Font(
      R.font.euclid_circular_b_regular,
      style = FontStyle.Normal,
      weight = FontWeight.W400,
    ),

    Font(
      R.font.euclid_circular_b_regular,
      style = FontStyle.Normal,
      weight = FontWeight.W500,
    ),
  )
}
