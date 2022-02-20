package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ipcoding.colorfulshoppinglist.R

@Composable
fun PicturePart(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_image),
            contentDescription = stringResource(id = R.string.icon_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(this.maxWidth),
        )
    }
}