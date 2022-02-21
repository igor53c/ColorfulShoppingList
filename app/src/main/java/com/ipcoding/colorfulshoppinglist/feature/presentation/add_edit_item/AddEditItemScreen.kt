package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.core.util.TestTags
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
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
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
        backgroundColor = AppTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            TopRow(
                onIconClick = { viewModel.onEvent(AddEditItemEvent.SaveItem) },
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
                modifier = Modifier.fillMaxHeight(),
                testTag = TestTags.TITLE_TEXT_FIELD
            )
        }
    }
}