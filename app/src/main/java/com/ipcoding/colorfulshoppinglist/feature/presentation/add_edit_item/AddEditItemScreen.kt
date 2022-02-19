package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import com.ipcoding.einkaufsliste.core.util.TestTags
import com.ipcoding.einkaufsliste.feature_item.domain.model.Item
import com.ipcoding.einkaufsliste.feature_item.presentation.add_edit_item.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditItemScreen(
    navController: NavController,
    itemColor: Int,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val titleState = viewModel.itemTitle.value

    val scaffoldState = rememberScaffoldState()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (itemColor != -1) itemColor else viewModel.itemColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditItemViewModel.UiEvent.ShowSnackbar -> {
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
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(AppTheme.dimensions.spaceMedium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimensions.spaceSmall),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Item.itemColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(AppTheme.dimensions.spaceLarge)
                            .shadow(AppTheme.dimensions.spaceMedium, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = AppTheme.dimensions.spaceSuperSmall,
                                color = if (viewModel.itemColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditItemEvent.ChangeColor(colorInt))
                            }
                    )
                }
                IconButton(
                    onClick = { viewModel.onEvent(AddEditItemEvent.SaveItem) },
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = "Save",
                        tint = AppTheme.colors.onSurface,
                        modifier = Modifier
                            .size(AppTheme.dimensions.spaceLarge)
                    )
                }
            }
            Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceMedium))
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