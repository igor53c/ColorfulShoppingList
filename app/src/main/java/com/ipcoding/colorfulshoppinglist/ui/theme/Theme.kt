package com.ipcoding.colorfulshoppinglist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkAppColorPalette else LightAppColorPalette

    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val typography = when (screenWidth) {
        in 0.dp..390.dp -> SmallTypography
        in 391.dp..500.dp -> NormalTypography
        in 501.dp..700.dp -> LargeTypography
        else -> ExtraLargeTypography
    }

    val dimensions = when (screenWidth) {
        in 0.dp..390.dp -> SmallDimensions
        in 391.dp..500.dp -> NormalDimensions
        in 501.dp..700.dp -> LargeDimensions
        else -> ExtraLargeDimensions
    }

    val shapes = when (screenWidth) {
        in 0.dp..390.dp -> SmallShapes
        in 391.dp..500.dp -> NormalShapes
        in 501.dp..700.dp -> LargeShapes
        else -> ExtraLargeShapes
    }

    AppTheme(
        colors = colors,
        typography = typography,
        dimensions = dimensions,
        customShapes = shapes,
        content = content
    )
}

@Composable
fun AppTheme(
    colors: AppColors = AppTheme.colors,
    typography: Typography = AppTheme.typography,
    dimensions: Dimensions = AppTheme.dimensions,
    customShapes: CustomShapes = AppTheme.customShapes,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalAppColors provides rememberedColors,
        LocalAppDimens provides dimensions,
        LocalAppTypography provides typography,
        LocalAppShapes provides customShapes
    ) {
        content()
    }
}

object AppTheme {

    val typography: Typography
        @Composable
        get() = LocalAppTypography.current

    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val dimensions: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val customShapes: CustomShapes
        @Composable
        get() = LocalAppShapes.current
}