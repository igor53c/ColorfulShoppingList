package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.feature.domain.model.Item
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsEvent
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsViewModel
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@Composable
fun ItemsRow(
    rowIndex: Int,
    items: List<Item>,
    navController: NavController,
    viewModel: ItemsViewModel
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
                    viewModel.onEvent(ItemsEvent.OnDeleteItemClick(item1))
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
                        viewModel.onEvent(ItemsEvent.OnDeleteItemClick(item2))
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