package com.ipcoding.colorfulshoppinglist.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ipcoding.colorfulshoppinglist.R

private val AppFontFamily = FontFamily(
    fonts = listOf(
        Font(R.font.signika_medium),
        Font(R.font.signika_bold, weight = FontWeight.Bold),
        Font(R.font.signika_light, weight = FontWeight.Light),
        Font(R.font.signika_regular, weight = FontWeight.Thin)
    )
)

private val DefaultTypography = Typography()

val NormalTypography = Typography(
    h1 = DefaultTypography.h1.copy(fontFamily = AppFontFamily, fontWeight = FontWeight.Normal),
    h2 = DefaultTypography.h2.copy(fontFamily = AppFontFamily, fontWeight = FontWeight.Normal),
    h3 = DefaultTypography.h3.copy(fontFamily = AppFontFamily),
    h4 = DefaultTypography.h4.copy(fontFamily = AppFontFamily),
    h5 = DefaultTypography.h5.copy(fontFamily = AppFontFamily),
    h6 = DefaultTypography.h6.copy(fontFamily = AppFontFamily),
    subtitle1 = DefaultTypography.subtitle1.copy(fontFamily = AppFontFamily),
    subtitle2 = DefaultTypography.subtitle2.copy(fontFamily = AppFontFamily),
    body1 = DefaultTypography.body1.copy(fontFamily = AppFontFamily),
    body2 = DefaultTypography.body2.copy(fontFamily = AppFontFamily),
    button = DefaultTypography.button.copy(fontFamily = AppFontFamily),
    caption = DefaultTypography.caption.copy(fontFamily = AppFontFamily, letterSpacing = 0.sp),
    overline = DefaultTypography.overline.copy(fontFamily = AppFontFamily)
)

val SmallTypography = Typography(
    h1 = NormalTypography.h1.copy(fontSize = 72.sp),
    h2 = NormalTypography.h2.copy(fontSize = 45.sp),
    h3 = NormalTypography.h3.copy(fontSize = 36.sp),
    h4 = NormalTypography.h4.copy(fontSize = 25.sp),
    h5 = NormalTypography.h5.copy(fontSize = 18.sp),
    h6 = NormalTypography.h6.copy(fontSize = 15.sp),
    subtitle1 = NormalTypography.subtitle1.copy(fontSize = 12.sp),
    subtitle2 = NormalTypography.subtitle2.copy(fontSize = 10.sp),
    body1 = NormalTypography.body1.copy(fontSize = 12.sp),
    body2 = NormalTypography.body2.copy(fontSize = 10.sp),
    button = NormalTypography.button.copy(fontSize = 10.sp),
    caption = NormalTypography.caption.copy(fontSize = 9.sp),
    overline = NormalTypography.overline.copy(fontSize = 7.sp)
)

val LargeTypography = Typography(
    h1 = NormalTypography.h1.copy(fontSize = 144.sp),
    h2 = NormalTypography.h2.copy(fontSize = 90.sp),
    h3 = NormalTypography.h3.copy(fontSize = 72.sp),
    h4 = NormalTypography.h4.copy(fontSize = 48.sp),
    h5 = NormalTypography.h5.copy(fontSize = 36.sp),
    h6 = NormalTypography.h6.copy(fontSize = 30.sp),
    subtitle1 = NormalTypography.subtitle1.copy(fontSize = 24.sp),
    subtitle2 = NormalTypography.subtitle2.copy(fontSize = 21.sp),
    body1 = NormalTypography.body1.copy(fontSize = 24.sp),
    body2 = NormalTypography.body2.copy(fontSize = 21.sp),
    button = NormalTypography.button.copy(fontSize = 21.sp),
    caption = NormalTypography.caption.copy(fontSize = 18.sp),
    overline = NormalTypography.overline.copy(fontSize = 15.sp)
)

val ExtraLargeTypography = Typography(
    h1 = NormalTypography.h1.copy(fontSize = 192.sp),
    h2 = NormalTypography.h2.copy(fontSize = 120.sp),
    h3 = NormalTypography.h3.copy(fontSize = 96.sp),
    h4 = NormalTypography.h4.copy(fontSize = 64.sp),
    h5 = NormalTypography.h5.copy(fontSize = 48.sp),
    h6 = NormalTypography.h6.copy(fontSize = 40.sp),
    subtitle1 = NormalTypography.subtitle1.copy(fontSize = 32.sp),
    subtitle2 = NormalTypography.subtitle2.copy(fontSize = 28.sp),
    body1 = NormalTypography.body1.copy(fontSize = 32.sp),
    body2 = NormalTypography.body2.copy(fontSize = 28.sp),
    button = NormalTypography.button.copy(fontSize = 28.sp),
    caption = NormalTypography.caption.copy(fontSize = 24.sp),
    overline = NormalTypography.overline.copy(fontSize = 20.sp)
)

val LocalAppTypography = staticCompositionLocalOf { NormalTypography }