package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.Drawer
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.IconsRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.components.ItemRow
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemsScreen(
    navController: NavController,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = AppTheme.colors.background,
        drawerContent = { Drawer(scope)},
        drawerBackgroundColor = AppTheme.colors.background,
        drawerShape = AppTheme.customShapes.roundedCornerShape,
        drawerScrimColor = AppTheme.colors.primary,
        drawerContentColor = AppTheme.colors.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.spaceMedium)
        ) {
            IconsRow(
                viewModel = viewModel,
                state = state,
                navController = navController,
                scope = scope,
                scaffoldState = scaffoldState
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceMedium))

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