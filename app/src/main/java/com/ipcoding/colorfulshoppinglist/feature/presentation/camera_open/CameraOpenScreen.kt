package com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.ipcoding.colorfulshoppinglist.R
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraOpenScreen(directory: File) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceSmall))

        val cameraPermissionState =
            rememberPermissionState(permission = Manifest.permission.CAMERA)

        Button(
            onClick = {
                cameraPermissionState.launchPermissionRequest()
            }
        ) {
            Text(text = "Permission")
        }

        Spacer(modifier = Modifier.height(AppTheme.dimensions.spaceExtraMedium))

        CameraOpen(directory)
    }
}

@Composable
fun CameraOpen(directory: File) {


    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    SimpleCameraPreview(
        context = context,
        lifecycleOwner = lifecycleOwner,
        outputDirectory = directory,
        onMediaCaptured = { url -> }
    )
}

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
    val camera: Camera? = null
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }
    var flashEnabled by remember { mutableStateOf(false) }
    var flashRes by remember { mutableStateOf(R.drawable.ic_outline_flashlight_on) }
    val executor = ContextCompat.getMainExecutor(context)
    var cameraSelector: CameraSelector
    val cameraProvider = cameraProviderFuture.get()

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
                        .build()

                    cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageCapture,
                        preview
                    )
                }, executor)
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                previewView
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spaceMedium)
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = {
                    Toast.makeText(context, "Back Clicked", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back arrow",
                    tint = AppTheme.colors.surface
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spaceMedium)
                .clip(AppTheme.customShapes.roundedCornerShape)
                .background(
                    color = AppTheme.colors.primary,
                    shape = AppTheme.customShapes.roundedCornerShape)
                .padding(AppTheme.dimensions.spaceSmall)
                .align(Alignment.BottomCenter)
        ) {
            IconButton(
                onClick = {
                    camera?.let {
                        if (it.cameraInfo.hasFlashUnit()) {
                            flashEnabled = !flashEnabled
                            flashRes = if (flashEnabled) R.drawable.ic_outline_flashlight_off else
                                R.drawable.ic_outline_flashlight_on
                            it.cameraControl.enableTorch(flashEnabled)
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = flashRes),
                    contentDescription = "",
                    modifier = Modifier.size(AppTheme.dimensions.spaceExtraMedium),
                    tint = AppTheme.colors.surface
                )
            }

            Button(
                onClick = {
                    val imgCapture = imageCapture ?: return@Button
                    val photoFile = File(
                        outputDirectory,
                        SimpleDateFormat("yyyyMMDD-HHmmss", Locale.US)
                            .format(System.currentTimeMillis()) + ".jpg"
                    )
                    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
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
                                Toast.makeText(context, "Something went wrong",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                },
                modifier = Modifier
                    .size(AppTheme.dimensions.spaceExtraLarge)
                    .background(
                        color = AppTheme.colors.primary,
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = AppTheme.dimensions.spaceExtraSmall,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .border(
                        width = AppTheme.dimensions.spaceExtraSmall,
                        color = Color.LightGray,
                        shape = CircleShape
                    ),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primary),
            ) {

            }

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
                        cameraSelector,
                        imageCapture,
                        preview
                    )
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_rotate),
                    contentDescription = "",
                    modifier = Modifier.size(AppTheme.dimensions.spaceExtraMedium),
                    tint = AppTheme.colors.surface
                )
            }
        }
    }
}

private class FaceAnalyzer: ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val imagePic = image.image
        imagePic?.close()
    }
}