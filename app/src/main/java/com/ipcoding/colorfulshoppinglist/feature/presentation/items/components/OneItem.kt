package com.ipcoding.einkaufsliste.feature_item.presentation.items.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import com.ipcoding.einkaufsliste.core.util.TestTags
import com.ipcoding.einkaufsliste.feature_item.domain.model.Item

@Composable
fun OneItem(
    item: Item,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = AppTheme.dimensions.spaceSmall,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .testTag(TestTags.NOTE_ITEM)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(item.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.spaceMedium)
                .padding(end = AppTheme.dimensions.spaceMedium)
        ) {
            Text(
                text = item.title,
                style = AppTheme.typography.h6,
                color = AppTheme.colors.onSurface,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete item",
                tint = AppTheme.colors.onSurface
            )
        }
    }
}
