package com.ipcoding.colorfulshoppinglist.feature.domain.use_case

import androidx.compose.ui.graphics.Color
import com.ipcoding.colorfulshoppinglist.ui.theme.Colors

class ChangeColor {

    operator fun invoke(color: Color) : Color {

        return when(color) {
            Colors.RedPink -> Color.Transparent
            Color.Transparent -> Colors.RedPink
            else -> Color.Transparent
        }
    }
}