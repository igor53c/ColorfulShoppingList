package com.ipcoding.colorfulshoppinglist.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val SmallShapes = CustomShapes(roundedCornerShape = RoundedCornerShape(12.dp))

val NormalShapes = CustomShapes(roundedCornerShape = RoundedCornerShape(16.dp))

val LargeShapes = CustomShapes(roundedCornerShape = RoundedCornerShape(24.dp))

val ExtraLargeShapes = CustomShapes(roundedCornerShape = RoundedCornerShape(32.dp))

@Immutable
class CustomShapes(
    val roundedCornerShape: CornerBasedShape = RoundedCornerShape(16.dp),
)

val LocalAppShapes = staticCompositionLocalOf { NormalShapes }