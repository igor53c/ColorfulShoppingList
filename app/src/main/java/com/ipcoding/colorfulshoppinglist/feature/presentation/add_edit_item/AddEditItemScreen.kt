package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components.TopRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components.TransparentHintTextField
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditItemScreen(
    navController: NavController,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val titleState = viewModel.itemTitle.value
    val currentIsMarked = viewModel.currentIsMarked.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditItemViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditItemViewModel.UiEvent.SaveItem -> {
                    navController.navigate(Screen.ItemsScreen.route)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = AppTheme.colors.background,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    snackbarData = data,
                    backgroundColor = AppTheme.colors.secondary,
                    contentColor = AppTheme.colors.primary
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            TopRow(
                onSaveClick = { viewModel.onEvent(AddEditItemEvent.SaveItem) },
                onMarkingClick = { viewModel.onEvent(AddEditItemEvent.MarkingItem) },
                isMarked = currentIsMarked,
                navController = navController
            )
            Divider(
                thickness = AppTheme.dimensions.spaceSuperSmall,
                color = AppTheme.colors.primary
            )
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditItemEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditItemEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                textStyle = AppTheme.typography.h5,
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
}