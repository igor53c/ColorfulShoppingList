package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import android.net.Uri
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ipcoding.colorfulshoppinglist.core.util.TestTags
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.ui.theme.Colors
import java.io.File

@Composable
fun OneItem(
    item: Item,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val textColor = remember { mutableStateOf(Colors.Blue)}
    val backgroundColor = remember { mutableStateOf(Color.Transparent)}
    val image = remember { mutableStateOf<Uri?>(null) }

    item.url?.let {
        image.value = Uri.fromFile(File(it))
    }

    if(item.isMarked) {
        backgroundColor.value = Color(Colors.Blue.toArgb())
        textColor.value = Color(AppTheme.colors.background.toArgb())
    } else {
        backgroundColor.value = Color(AppTheme.colors.background.toArgb())
        textColor.value = Color(Colors.Blue.toArgb())
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

            val data = if (image.value != null) image.value else R.drawable.ic_image

            Image(
                contentDescription = stringResource(id = R.string.icon_image),
                painter = rememberImagePainter(
                    data = data,
                    builder = {
                        placeholder(R.drawable.ic_image)
                        crossfade(300)
                        transformations(
                            RoundedCornersTransformation(AppTheme.dimensions.spaceLarge.value)
                        )
                    }
                ),
                contentScale = ContentScale.Crop,
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
