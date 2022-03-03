package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.DeleteItemView
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.IconsRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.ItemsRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.OneItemWithoutImage
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun ItemsScreen(
    navController: NavController,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val isImageDisplayed = viewModel.isImageDisplayed.value
    val isDeleteDialogDisplayed = viewModel.isDeleteDialogDisplayed.value

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
                            viewModel = viewModel
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
                            modifier = Modifier.clickable {
                                viewModel.onEvent(ItemsEvent.UpdateItem(item))
                            },
                            item = item,
                            onEditClick = {
                                navController.navigate(
                                    Screen.AddEditItemScreen.route + "?itemId=${item.id}"
                                )
                            },
                            onDeleteClick = {
                                viewModel.onEvent(ItemsEvent.OnDeleteItemClick(item))
                            }
                        )

                        Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))
                    }
                }
            }
        }
    }

    if(isDeleteDialogDisplayed) {
        DeleteItemView(
            onDismissRequest = { viewModel.onEvent(ItemsEvent.CloseDeleteItemView) },
            onYesClick = { viewModel.onEvent(ItemsEvent.DeleteItem) },
            onNoClick = { viewModel.onEvent(ItemsEvent.CloseDeleteItemView) }
        )
    }
}