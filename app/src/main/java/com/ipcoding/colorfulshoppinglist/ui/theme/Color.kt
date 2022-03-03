package com.ipcoding.colorfulshoppinglist.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Green = Color(0xFF7CB342)
val Blue100 = Color(0xFF000DFF)
val Blue200 = Color(0xFF969CFF)

fun lightAppColors(
    primary: Color = Blue100,
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Blue200,
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
    primary: Color = Blue200,
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Blue100,
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