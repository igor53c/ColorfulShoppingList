package com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open

import androidx.lifecycle.ViewModel
import com.ipcoding.colorfulshoppinglist.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraOpenViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    fun saveCurrentUrl(url: String?) {
        preferences.saveCurrentUrl(url)
    }
}