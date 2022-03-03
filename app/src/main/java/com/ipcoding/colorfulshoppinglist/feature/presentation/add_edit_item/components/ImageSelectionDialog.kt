package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun ImageSelectionDialog(
    onDeleteClick: () -> Unit,
    onCameraClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    AlertDialog(
        backgroundColor = AppTheme.colors.primary,
        onDismissRequest = {},
        confirmButton = {
            Row(
                modifier = Modifier
                    .padding(AppTheme.dimensions.spaceMedium)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = onCameraClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = stringResource(id = R.string.icon_camera),
                        modifier = Modifier
                            .size(AppTheme.dimensions.spaceExtraLarge)
                            .weight(1f),
                        tint = AppTheme.colors.background
                    )
                }

                IconButton(
                    onClick = onDeleteClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.delete_picture),
                        modifier = Modifier
                            .size(AppTheme.dimensions.spaceExtraLarge)
                            .weight(1f),
                        tint = AppTheme.colors.background
                    )
                }

                IconButton(
                    onClick = onCancelClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = stringResource(id = R.string.cancel),
                        modifier = Modifier
                            .size(AppTheme.dimensions.spaceExtraLarge)
                            .weight(1f),
                        tint = AppTheme.colors.background
                    )
                }
            }
        }
    )
}