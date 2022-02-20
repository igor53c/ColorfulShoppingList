package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.core.util.TestTags
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components.TopRow
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components.TransparentHintTextField
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditItemScreen(
    navController: NavController,
    itemColor: Int,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val titleState = viewModel.itemTitle.value
    val scaffoldState = rememberScaffoldState()
    val color = remember {
        mutableStateOf(Color(if (itemColor != -1) itemColor else viewModel.itemColor.value))
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditItemViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditItemViewModel.UiEvent.SaveItem -> {
                    navController.navigateUp()
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
                color = color.value,
                onColorClick = {
                    color.value = viewModel.changeColor(color.value)
                    viewModel.onEvent(AddEditItemEvent.ChangeColor(color.value.toArgb()))
                },
                onIconClick = { viewModel.onEvent(AddEditItemEvent.SaveItem) }
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