package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import com.ipcoding.colorfulshoppinglist.R
import java.io.File

@Composable
fun OneItem(
    item: Item,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val textColor = remember { mutableStateOf(Color.Transparent)}
    val backgroundColor = remember { mutableStateOf(Color.Transparent)}
    val borderColor = remember { mutableStateOf(Color.Transparent)}
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    imageUri.value = if(item.url == null) null else
        item.url?.run { Uri.fromFile(File(this)) }

    if(item.isMarked) {
        backgroundColor.value = AppTheme.colors.primary
        textColor.value = AppTheme.colors.background
        borderColor.value = Color.Transparent
    } else {
        backgroundColor.value = AppTheme.colors.background
        textColor.value = AppTheme.colors.primary
        borderColor.value = AppTheme.colors.primary
    }
    BoxWithConstraints(
        modifier = modifier
            .background(
                color = backgroundColor.value,
                shape = AppTheme.customShapes.roundedCornerShape
            )
            .border(
                width = AppTheme.dimensions.spaceExtraSmall,
                color = borderColor.value,
                shape = AppTheme.customShapes.roundedCornerShape
            )
    ) {
        val size = this.maxWidth - AppTheme.dimensions.spaceLarge

        OneItemIcon(
            modifier = Modifier.align(alignment = Alignment.TopEnd),
            imageVector = Icons.Default.EditNote,
            contentDescription = stringResource(id = R.string.edit_item),
            tint = textColor.value,
            onClick = onEditClick
        )

        OneItemIcon(
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(id = R.string.delete_item),
            tint = textColor.value,
            onClick = onDeleteClick
        )

        Column(
            modifier = Modifier
                .padding(
                    start = AppTheme.dimensions.spaceSmall,
                    top = AppTheme.dimensions.spaceSmall,
                    bottom = AppTheme.dimensions.spaceSmall,
                )
                .fillMaxSize()
        ) {

            Image(
                contentDescription = stringResource(id = R.string.icon_image),
                painter = rememberImagePainter(
                    data = if (imageUri.value != null) imageUri.value else R.drawable.image_icon,
                    builder = {
                        placeholder(R.drawable.image_icon)
                        transformations(
                            RoundedCornersTransformation(AppTheme.dimensions.spaceLarge.value)
                        )
                    }
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size),
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))

            Text(
                text = item.title,
                style = AppTheme.typography.h6,
                color = textColor.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(size)
            )
        }
    }
}