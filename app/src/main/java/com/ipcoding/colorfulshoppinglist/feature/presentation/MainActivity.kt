package com.ipcoding.colorfulshoppinglist.feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.AddEditItemScreen
import com.ipcoding.colorfulshoppinglist.feature.presentation.camera_open.CameraOpenScreen
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsScreen
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                   color = AppTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ItemsScreen.route
                    ) {
                        composable(route = Screen.ItemsScreen.route) {
                            ItemsScreen(navController = navController)
                            BackHandler(true) {}
                        }
                        composable(
                            route = Screen.AddEditItemScreen.route +
                                    "?itemId={itemId}",
                            arguments = listOf(
                                navArgument(
                                    name = "itemId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditItemScreen(navController = navController)
                            BackHandler(true) {
                                navController.navigate(Screen.ItemsScreen.route)
                            }
                        }
                        composable(route = Screen.CameraOpenScreen.route) {
                            CameraOpenScreen(
                                navController = navController,
                                directory = getDirectory()
                            )
                            BackHandler(true) { navController.navigateUp() }
                        }
                    }
                }
            }
        }
    }

    //Store the capture image
    private fun getDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }
}