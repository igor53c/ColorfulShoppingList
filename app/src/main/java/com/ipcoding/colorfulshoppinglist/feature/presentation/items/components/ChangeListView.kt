package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsEvent
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsViewModel
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun ChangeListView(
    viewModel: ItemsViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit
) {
    val isImageDisplayed = viewModel.isImageDisplayed.value

    AlertDialog(
        backgroundColor = AppTheme.colors.secondary,
        onDismissRequest = onDismissRequest,
        text = {
            Text(
                text = stringResource(id = R.string.show_items),
                style = AppTheme.typography.h5,
                color = AppTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .height(AppTheme.dimensions.spaceLarge)
                    .fillMaxWidth()
            )
        },
        confirmButton = {
            Column(
                modifier = Modifier
                    .padding(bottom = AppTheme.dimensions.spaceSmall),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isImageDisplayed,
                        onClick = {
                            viewModel.onEvent(ItemsEvent.ItemWithImage)
                            onDismissRequest()
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = AppTheme.colors.primary,
                            unselectedColor = AppTheme.colors.primary
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.with_image),
                        style = AppTheme.typography.h6,
                        color = AppTheme.colors.primary,
                        modifier = Modifier
                            .clickable(onClick = {
                                viewModel.onEvent(ItemsEvent.ItemWithImage)
                                onDismissRequest()
                            })
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = !isImageDisplayed,
                        onClick = {
                            viewModel.onEvent(ItemsEvent.ItemWithoutImage)
                            onDismissRequest()
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = AppTheme.colors.primary,
                            unselectedColor = AppTheme.colors.primary
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.without_image),
                        style = AppTheme.typography.h6,
                        color = AppTheme.colors.primary,
                        modifier = Modifier
                            .clickable(onClick = {
                                viewModel.onEvent(ItemsEvent.ItemWithoutImage)
                                onDismissRequest()
                            })
                    )
                }
            }
        }
    )
}