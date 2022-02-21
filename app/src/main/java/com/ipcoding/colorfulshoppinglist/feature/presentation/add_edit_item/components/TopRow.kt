package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun TopRow(
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PicturePart(
            modifier = Modifier.weight(1f),
            navController = navController
        )

        IconButton(
            onClick = onIconClick,
            modifier = Modifier
                .padding(end = AppTheme.dimensions.spaceMedium)
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = stringResource(id = R.string.save),
                tint = AppTheme.colors.primary,
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceExtraLarge)
            )
        }
    }
}