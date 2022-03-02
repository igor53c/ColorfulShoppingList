package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun OneItemIcon(
    modifier: Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    tint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(AppTheme.dimensions.spaceExtraSmall)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = AppTheme.dimensions.spaceMedium
                ),
                onClick = onClick
            )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier
                .size(AppTheme.dimensions.spaceExtraMedium)
        )
    }
}