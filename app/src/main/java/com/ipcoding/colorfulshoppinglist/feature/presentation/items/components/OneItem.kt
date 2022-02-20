package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ipcoding.colorfulshoppinglist.core.util.TestTags
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.ui.theme.Colors

@Composable
fun OneItem(
    item: Item,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val textColor = remember { mutableStateOf(Colors.RedPink)}
    val backgroundColor = remember { mutableStateOf(Color.Transparent)}

    if(item.color == Colors.RedPink.toArgb()) {
        backgroundColor.value = Color(Colors.RedPink.toArgb())
        textColor.value = Color(AppTheme.colors.background.toArgb())
    } else {
        backgroundColor.value = Color(AppTheme.colors.background.toArgb())
        textColor.value = Color(Colors.RedPink.toArgb())
    }
    BoxWithConstraints(
        modifier = modifier
            .testTag(TestTags.NOTE_ITEM)
    ) {
        val imageHeight = this.maxWidth
        Column(
            modifier = Modifier
                .background(
                    color = backgroundColor.value,
                    shape = AppTheme.customShapes.roundedCornerShape
                )
                .border(
                    width = AppTheme.dimensions.spaceSuperSmall,
                    color = textColor.value,
                    shape = AppTheme.customShapes.roundedCornerShape
                )
                .fillMaxSize()
                .padding(end = AppTheme.dimensions.spaceMedium)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = stringResource(id = R.string.icon_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight),
            )

            Text(
                text = item.title,
                style = AppTheme.typography.h6,
                color = textColor.value,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(AppTheme.dimensions.spaceMedium)
            )
        }
        IconButton(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.EditNote,
                contentDescription = stringResource(id = R.string.edit_item),
                tint = textColor.value
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_item),
                tint = textColor.value
            )
        }
    }
}
