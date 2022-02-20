package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.feature.domain.util.ItemOrder
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.ItemRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsScreen(
    navController: NavController,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = AppTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.spaceMedium)
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
                            ItemsEvent.Order(ItemOrder.Color(state.itemOrder.orderType))
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
                IconButton(
                    onClick = {
                        navController.navigate(Screen.AddEditItemScreen.route)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_item),
                        modifier = Modifier
                            .size(AppTheme.dimensions.spaceLarge),
                        tint = AppTheme.colors.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                if(state.items.isNotEmpty()) {
                    val itemCount = if (state.items.size % 2 == 0) {
                        state.items.size / 2
                    } else {
                        state.items.size / 2 + 1
                    }
                    items(itemCount) { rowIndex ->
                        ItemRow(
                            rowIndex = rowIndex,
                            items = state.items,
                            navController = navController,
                            viewModel = viewModel,
                            scaffoldState = scaffoldState
                        )
                        Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))
                    }
                }
            }
        }
    }
}