package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.IconsRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.ItemsRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.OneItemWithoutImage
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ItemsScreen(
    navController: NavController,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isImageDisplayed = viewModel.isImageDisplayed.value
    val textItemDeleted = stringResource(id = R.string.item_deleted)
    val textUndo = stringResource(id = R.string.undo)

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = AppTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.spaceMedium)
        ) {
            IconsRow(
                viewModel = viewModel,
                state = state,
                navController = navController
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceMedium))

            if(isImageDisplayed) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    if(state.items.isNotEmpty()) {
                        val itemCount = if (state.items.size % 2 == 0) {
                            state.items.size / 2
                        } else {
                            state.items.size / 2 + 1
                        }
                        items(itemCount) { rowIndex ->
                            ItemsRow(
                                rowIndex = rowIndex,
                                items = state.items,
                                navController = navController,
                                viewModel = viewModel,
                                scaffoldState = scaffoldState,
                                scope = scope,
                                textItemDeleted = textItemDeleted,
                                textUndo = textUndo
                            )
                            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))
                        }
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    if(state.items.isNotEmpty()) {
                        val items = state.items
                        items(items.size) { number ->
                            val item = items[number]

                            OneItemWithoutImage(
                                item = item,
                                onEditClick = {
                                    navController.navigate(
                                        Screen.AddEditItemScreen.route + "?itemId=${item.id}"
                                    )
                                },
                                onDeleteClick = {
                                    viewModel.onEvent(ItemsEvent.DeleteItem(item))
                                    scope.launch {
                                        val result = scaffoldState.snackbarHostState.showSnackbar(
                                            message = textItemDeleted,
                                            actionLabel = textUndo
                                        )
                                        if(result == SnackbarResult.ActionPerformed) {
                                            viewModel.onEvent(ItemsEvent.RestoreItem)
                                        }
                                    }
                                }
                            )

                            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))
                        }
                    }
                }
            }
        }
    }
}