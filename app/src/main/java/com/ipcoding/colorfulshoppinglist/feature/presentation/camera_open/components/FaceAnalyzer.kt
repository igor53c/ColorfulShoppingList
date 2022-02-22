package com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open.components

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class FaceAnalyzer : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val imagePic = image.image
        imagePic?.close()
    }
}