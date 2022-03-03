package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.components

import android.Manifest
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.core.util.Constants.CROSSFADE_DURATION_MILLIS
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.AddEditItemEvent
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.AddEditItemViewModel
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PicturePart(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val currentUrl = viewModel.currentUrl.value
    val openDialog = remember { mutableStateOf(false) }
    val selectedImage = remember { mutableStateOf<Uri?>(null) }
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    if (currentUrl != null) selectedImage.value = Uri.fromFile(File(currentUrl))

    if (openDialog.value) {
        ImageSelectionDialog(
            onCameraClick = {
                navController.navigate(Screen.CameraOpenScreen.route)
                cameraPermissionState.launchPermissionRequest()
            },
            onDeleteClick = {
                viewModel.onEvent(AddEditItemEvent.DeleteImage(url = currentUrl))
                selectedImage.value = null
                openDialog.value = false
            },
            onCancelClick = {
                openDialog.value = false
            }
        )
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        Image(
            contentDescription = stringResource(id = R.string.icon_image),
            painter = rememberImagePainter(
                data = if (selectedImage.value != null) selectedImage.value else R.drawable.image_icon,
                builder = {
                    placeholder(R.drawable.image_icon)
                    crossfade(CROSSFADE_DURATION_MILLIS)
                    transformations(
                        RoundedCornersTransformation(AppTheme.dimensions.spaceLarge.value)
                    )
                }
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(this.maxWidth)
                .padding(AppTheme.dimensions.spaceLarge)
                .fillMaxWidth()
                .clickable {
                    openDialog.value = true
                },
        )
    }
}