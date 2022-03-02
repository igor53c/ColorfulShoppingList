package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun OneItemWithoutImage(
    item: Item,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val textColor = remember { mutableStateOf(Color.Transparent) }
    val backgroundColor = remember { mutableStateOf(Color.Transparent) }
    val borderColor = remember { mutableStateOf(Color.Transparent) }

    if(item.isMarked) {
        backgroundColor.value = AppTheme.colors.primary
        textColor.value = AppTheme.colors.background
        borderColor.value = Color.Transparent
    } else {
        backgroundColor.value = AppTheme.colors.background
        textColor.value = AppTheme.colors.primary
        borderColor.value = AppTheme.colors.primary
    }

    Row(
        modifier = Modifier
            .background(
                color = backgroundColor.value,
                shape = AppTheme.customShapes.roundedCornerShape
            )
            .border(
                width = AppTheme.dimensions.spaceExtraSmall,
                color = borderColor.value,
                shape = AppTheme.customShapes.roundedCornerShape
            )
            .height(AppTheme.dimensions.spaceExtraLarge)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.title,
            style = AppTheme.typography.h5,
            color = textColor.value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = AppTheme.dimensions.spaceMedium)
                .weight(1f)
        )

        OneItemIcon(
            modifier = Modifier,
            imageVector = Icons.Default.EditNote,
            contentDescription = stringResource(id = R.string.edit_item),
            tint = textColor.value,
            onClick = onEditClick
        )

        OneItemIcon(
            modifier = Modifier
                .padding(end = AppTheme.dimensions.spaceSmall),
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(id = R.string.delete_item),
            tint = textColor.value,
            onClick = onDeleteClick
        )
    }
}