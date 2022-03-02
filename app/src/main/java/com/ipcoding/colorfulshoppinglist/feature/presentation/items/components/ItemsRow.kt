package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsEvent
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsViewModel
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ItemsRow(
    rowIndex: Int,
    items: List<Item>,
    navController: NavController,
    viewModel: ItemsViewModel,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    textItemDeleted: String,
    textUndo: String,
) {
    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            val item1 = items[rowIndex * 2]
            OneItem(
                item = item1,
                modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.onEvent(ItemsEvent.UpdateItem(item1)) },
                onDeleteClick = {
                    viewModel.onEvent(ItemsEvent.DeleteItem(item1))
                    scope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = textItemDeleted,
                            actionLabel = textUndo
                        )
                        if(result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(ItemsEvent.RestoreItem)
                        }
                    }
                },
                onEditClick = {
                    navController.navigate(
                        Screen.AddEditItemScreen.route + "?itemId=${item1.id}"
                    )
                }
            )

            Spacer(modifier = Modifier.width(AppTheme.dimensions.spaceMedium))

            if(items.size >= rowIndex * 2 + 2) {
                val item2 = items[rowIndex * 2 + 1]
                OneItem(
                    item = item2,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { viewModel.onEvent(ItemsEvent.UpdateItem(item2)) },
                    onDeleteClick = {
                        viewModel.onEvent(ItemsEvent.DeleteItem(item2))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = textItemDeleted,
                                actionLabel = textUndo,
                                duration = SnackbarDuration.Long
                            )
                            if(result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(ItemsEvent.RestoreItem)
                            }
                        }
                    },
                    onEditClick = {
                        navController.navigate(
                            Screen.AddEditItemScreen.route + "?itemId=${item2.id}"
                        )
                    }
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))
    }
}