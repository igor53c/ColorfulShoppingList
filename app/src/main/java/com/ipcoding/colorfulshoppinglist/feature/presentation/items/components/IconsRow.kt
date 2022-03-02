package com.ipcoding.colorfulshoppinglist.feature.presentation.items.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.feature.domain.util.ItemOrder
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsEvent
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsState
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsViewModel
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun IconsRow(
    viewModel: ItemsViewModel,
    state: ItemsState,
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                viewModel.onEvent(
                    ItemsEvent.Order(ItemOrder.Title(state.itemOrder.orderType))
                )
            },
        ) {
            Icon(
                imageVector = Icons.Default.SortByAlpha,
                contentDescription = stringResource(id = R.string.sort_by_alpha),
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceLarge),
                tint = AppTheme.colors.primary
            )
        }
        IconButton(
            onClick = {
                viewModel.onEvent(
                    ItemsEvent.Order(ItemOrder.IsMarked(state.itemOrder.orderType))
                )
            },
        ) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = stringResource(id = R.string.sort_by_color),
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceLarge),
                tint = AppTheme.colors.primary
            )
        }
        IconButton(onClick = { navController.navigate(Screen.AddEditItemScreen.route) }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_item),
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceLarge),
                tint = AppTheme.colors.primary
            )
        }
        IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(id = R.string.add_item),
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceLarge),
                tint = AppTheme.colors.primary
            )
        }
    }
}