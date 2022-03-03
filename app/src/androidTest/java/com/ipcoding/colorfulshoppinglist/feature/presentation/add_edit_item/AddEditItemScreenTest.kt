package com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item

import androidx.activity.compose.BackHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.di.AppModule
import com.ipcoding.colorfulshoppinglist.feature.presentation.MainActivity
import com.ipcoding.colorfulshoppinglist.feature.presentation.items.ItemsScreen
import com.ipcoding.colorfulshoppinglist.feature.presentation.util.Screen
import com.ipcoding.colorfulshoppinglist.ui.theme.AppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class AddEditItemScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController
    private lateinit var save: String
    private lateinit var itemCantEmpty: String
    private lateinit var addItem: String

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            save = stringResource(id = R.string.save)
            itemCantEmpty = stringResource(id = R.string.item_cant_empty)
            addItem = stringResource(id = R.string.add_item)
            navController = rememberNavController()
            AppTheme {
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
                }
            }
        }
    }

    @Test
    fun emptyText_throwsErrorMessage () {
        composeRule.onNodeWithContentDescription(addItem).performClick()
        composeRule.onNodeWithContentDescription(save).performClick()
        composeRule.onNodeWithText(itemCantEmpty).assertIsDisplayed()
    }
}