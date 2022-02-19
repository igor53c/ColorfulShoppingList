package com.ipcoding.colorfulshoppinglist.feature.presentation.util

sealed class Screen(val route: String) {
    object ItemsScreen: Screen("items_screen")
    object AddEditItemScreen: Screen("add_edit_item_screen")
    object CameraOpenScreen: Screen("camera_open_screen")
}