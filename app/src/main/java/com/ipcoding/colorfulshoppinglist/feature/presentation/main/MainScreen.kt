package com.ipcoding.colorfulshoppinglist.feature.presentation.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.OutlinedButton
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun MainApp() {
    val selectedImage = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImage.value = uri
    }

    MainContent(selectedImage.value) {
        launcher.launch("image/jpeg")
    }
}

@Composable
private fun MainContent(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
) {
    Scaffold() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedImage != null)
                Image(
                    painter = rememberImagePainter(selectedImage),
                    contentDescription = "Selected image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onImageClick()
                        })
            else
                OutlinedButton(onClick = onImageClick) {
                    Text(text = "Choose Image")
                }
        }
    }
}