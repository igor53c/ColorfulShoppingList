package com.ipcoding.colorfulshoppinglist.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Green = Color(0xFF4CAF50)
val RedPink = Color(0xFFE91E63)
val Orange = Color(0xFFFF9800)
val Navy = Color(0xFF000080)
val Purple = Color(0xFF6200EE)

object Colors {
    val LightGreen10 = Color(0xFFC2FDC2)
    val LightGreen20 = Color(0xFFACFAAC)
    val LightBlue = Color(0xFFC5C5FF)
    val LightYellow = Color(0xFFF3F3BD)
    val LightRed10 = Color(0xFFFDC8C8)
    val LightRed20 = Color(0xFFF7A8A8)
    val LightGray10 = Color(0xFFE2E2E2)
    val AllColors = listOf(
        Color.Black, Color.LightGray, Color.Green,
        Color.Cyan, Color.Blue, Navy, Purple, Color.Magenta,
        Color.Red, Orange, Color.Yellow, Color.White
    )

    fun colorToIndex(color: Color): Int {
        return AllColors.indexOf(color)
    }

    fun indexToColor(index: Int): Color {
        return when (index) {
            in AllColors.indices -> AllColors[index]
            else -> Color.Transparent
        }
    }
}

fun lightAppColors(
    primary: Color = RedPink,
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Color(0xFF03DAC6),
    secondaryVariant: Color = Green,
    background: Color = Color.White,
    surface: Color = Color.White,
    error: Color = Color(0xFFB00020),
    onPrimary: Color = Color.White,
    onSecondary: Color = Color.Black,
    onBackground: Color = Color.Black,
    onSurface: Color = Color.Black,
    onError: Color = Color.White
): AppColors = AppColors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    true
)

fun darkAppColors(
    primary: Color = RedPink,
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Color(0xFF03DAC6),
    secondaryVariant: Color = Green,
    background: Color = Color(0xFF121212),
    surface: Color = Color(0xFF121212),
    error: Color = Color(0xFFCF6679),
    onPrimary: Color = Color.Black,
    onSecondary: Color = Color.Black,
    onBackground: Color = Color.White,
    onSurface: Color = Color.White,
    onError: Color = Color.Black
): AppColors = AppColors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    false
)

val DarkAppColorPalette = darkAppColors()

val LightAppColorPalette = lightAppColors()

val LocalAppColors = staticCompositionLocalOf { LightAppColorPalette }