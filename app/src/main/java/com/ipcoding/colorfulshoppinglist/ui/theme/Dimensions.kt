package com.ipcoding.colorfulshoppinglist.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val default: Dp = 0.dp,
    val spaceSuperSmall: Dp = 2.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceExtraMedium: Dp = 32.dp,
    val spaceLarge: Dp = 48.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val spaceSuperLarge: Dp = 96.dp
)

val NormalDimensions = Dimensions()

val SmallDimensions = Dimensions(
    spaceSuperSmall = 2.dp,
    spaceExtraSmall = 3.dp,
    spaceSmall = 6.dp,
    spaceMedium = 12.dp,
    spaceExtraMedium = 24.dp,
    spaceLarge = 36.dp,
    spaceExtraLarge = 48.dp,
    spaceSuperLarge = 72.dp
)

val LargeDimensions = Dimensions(
    spaceSuperSmall = 3.dp,
    spaceExtraSmall = 6.dp,
    spaceSmall = 12.dp,
    spaceMedium = 24.dp,
    spaceExtraMedium = 48.dp,
    spaceLarge = 72.dp,
    spaceExtraLarge = 96.dp,
    spaceSuperLarge = 144.dp
)

val ExtraLargeDimensions = Dimensions(
    spaceSuperSmall = 4.dp,
    spaceExtraSmall = 8.dp,
    spaceSmall = 16.dp,
    spaceMedium = 32.dp,
    spaceExtraMedium = 64.dp,
    spaceLarge = 96.dp,
    spaceExtraLarge = 128.dp,
    spaceSuperLarge = 192.dp
)

val LocalAppDimens = staticCompositionLocalOf { NormalDimensions }