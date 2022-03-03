package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun DeleteItemView(
    onDismissRequest: () -> Unit,
    onYesClick: () -> Unit,
    onNoClick: () -> Unit
) {
    AlertDialog(
        backgroundColor = AppTheme.colors.secondary,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(id = R.string.really_delete_item),
                style = AppTheme.typography.h5,
                color = AppTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = AppTheme.dimensions.spaceMedium)
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimensions.spaceSmall)
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = onYesClick,
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = AppTheme.colors.primary)
                ) {
                    Text(
                        text = stringResource(id = R.string.yes),
                        style = AppTheme.typography.h5,
                        color = AppTheme.colors.background,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(AppTheme.dimensions.spaceSmall))

                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = onNoClick,
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = AppTheme.colors.primary)
                ) {
                    Text(
                        text = stringResource(id = R.string.no),
                        style = AppTheme.typography.h5,
                        color = AppTheme.colors.background,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}