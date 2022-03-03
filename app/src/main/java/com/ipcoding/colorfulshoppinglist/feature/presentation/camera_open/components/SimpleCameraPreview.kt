package com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open.components

import android.content.Context
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.FlashlightOff
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.core.util.Constants
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SimpleCameraPreview(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    outputDirectory: File,
    onMediaCaptured: (Uri?) -> Unit
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val camera = remember { mutableStateOf<Camera?>(null) }
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    var flashEnabled by remember { mutableStateOf(false) }
    var flashRes by remember { mutableStateOf(Icons.Filled.FlashlightOn) }
    val executor = remember(context) { ContextCompat.getMainExecutor(context) }
    var cameraSelector: CameraSelector? by remember { mutableStateOf(null) }
    val cameraProvider = remember(cameraProviderFuture) { cameraProviderFuture.get() }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val errorMessage = stringResource(id = R.string.error_has_occurred)

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = AppTheme.colors.background
    ) {
        Box {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    cameraProviderFuture.addListener({
                        val imageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()
                            .apply {
                                setAnalyzer(executor, FaceAnalyzer())
                            }
                        imageCapture = ImageCapture.Builder()
                            .setTargetRotation(previewView.display.rotation)
                            .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
                            .build()

                        cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()

                        cameraProvider.unbindAll()
                        camera.value = cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector as CameraSelector,
                            imageCapture,
                            preview,
                            imageAnalysis
                        )
                    }, executor)
                    preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    previewView
                }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RectangleShape)
                    .background(
                        color = AppTheme.colors.primary,
                        shape = RectangleShape
                    )
                    .padding(AppTheme.dimensions.spaceMedium)
                    .align(Alignment.BottomCenter)
            ) {
                IconButton(
                    onClick = {
                        camera.value?.let {
                            if (it.cameraInfo.hasFlashUnit()) {
                                flashEnabled = !flashEnabled
                                flashRes = if (flashEnabled) Icons.Filled.FlashlightOff else
                                    Icons.Filled.FlashlightOn
                                it.cameraControl.enableTorch(flashEnabled)
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = flashRes,
                        contentDescription = stringResource(id = R.string.flash_icon),
                        modifier = Modifier.size(AppTheme.dimensions.spaceExtraMedium),
                        tint = AppTheme.colors.surface
                    )
                }

                Button(
                    onClick = {
                        val imgCapture = imageCapture ?: return@Button
                        val photoFile = File(
                            outputDirectory,
                            SimpleDateFormat(Constants.IMAGE_OUTPUT_PATTERN, Locale.US)
                                .format(System.currentTimeMillis()) + Constants.IMAGE_FORMAT
                        )
                        val outputOptions =
                            ImageCapture.OutputFileOptions.Builder(photoFile).build()
                        imgCapture.takePicture(
                            outputOptions,
                            executor,
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(
                                    outputFileResults: ImageCapture.OutputFileResults
                                ) {
                                    onMediaCaptured(Uri.fromFile(photoFile))
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    scope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = errorMessage
                                        )
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier
                        .size(AppTheme.dimensions.spaceLarge)
                        .shadow(
                            elevation = AppTheme.dimensions.spaceExtraSmall,
                            shape = AppTheme.customShapes.roundedCornerShape
                        )
                        .clip(AppTheme.customShapes.roundedCornerShape),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppTheme.colors.background
                    )
                ) {}

                IconButton(
                    onClick = {
                        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK)
                            CameraSelector.LENS_FACING_FRONT else
                            CameraSelector.LENS_FACING_BACK

                        cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(lensFacing)
                            .build()
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector as CameraSelector,
                            imageCapture,
                            preview
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Cameraswitch,
                        contentDescription = stringResource(id = R.string.camera_switch_icon),
                        modifier = Modifier.size(AppTheme.dimensions.spaceExtraMedium),
                        tint = AppTheme.colors.surface
                    )
                }
            }
        }
    }
}