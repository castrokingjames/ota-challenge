/*
* Copyright 2024
*/
package ph.com.ota.challenge.ui.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import ph.com.ota.challenge.ui.compose.theme.Font.euclidCircularBRegular

class TypographyResource {

  val regular: TextStyle = TextStyle(
    fontFamily = euclidCircularBRegular,
    fontWeight = FontWeight.W500,
    fontSize = TextUnit(14f, TextUnitType.Sp),
    lineHeight = TextUnit(20f, TextUnitType.Sp),
  )

  val regularXXSmall: TextStyle = TextStyle(
    fontFamily = euclidCircularBRegular,
    fontWeight = FontWeight.W500,
    fontSize = TextUnit(12f, TextUnitType.Sp),
    lineHeight = TextUnit(18f, TextUnitType.Sp),
  )

  val medium: TextStyle = TextStyle(
    fontFamily = euclidCircularBRegular,
    fontWeight = FontWeight.W500,
    fontSize = TextUnit(18f, TextUnitType.Sp),
    lineHeight = TextUnit(30f, TextUnitType.Sp),
  )

  val mediumSmall: TextStyle = TextStyle(
    fontFamily = euclidCircularBRegular,
    fontWeight = FontWeight.W500,
    fontSize = TextUnit(16f, TextUnitType.Sp),
    lineHeight = TextUnit(26f, TextUnitType.Sp),
  )

  val mediumXXSmall: TextStyle = TextStyle(
    fontFamily = euclidCircularBRegular,
    fontWeight = FontWeight.W500,
    fontSize = TextUnit(12f, TextUnitType.Sp),
    lineHeight = TextUnit(18f, TextUnitType.Sp),
  )

  val mediumXXXSmall: TextStyle = TextStyle(
    fontFamily = euclidCircularBRegular,
    fontWeight = FontWeight.W500,
    fontSize = TextUnit(12f, TextUnitType.Sp),
    lineHeight = TextUnit(15f, TextUnitType.Sp),
  )
}

val Typography: TypographyResource @Composable get() = typographyComposition.current

val typographyComposition = compositionLocalOf { TypographyResource() }
