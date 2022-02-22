package com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open.components.SimpleCameraPreview
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraOpenScreen(
    navController: NavController,
    directory: File,
    viewModel: CameraOpenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    SimpleCameraPreview(
        context = context,
        lifecycleOwner = lifecycleOwner,
        outputDirectory = directory,
        onMediaCaptured = { uri ->
            viewModel.saveCurrentUrl(uri?.path)
            navController.navigate(Screen.AddEditItemScreen.route)
        }
    )
}