package com.ipcoding.colorfulshoppinglist.feature.presentation.items

import androidx.activity.compose.BackHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipcoding.colorfulshoppinglist.R
import com.ipcoding.colorfulshoppinglist.di.AppModule
import com.ipcoding.colorfulshoppinglist.feature.presentation.MainActivity
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.AddEditItemScreen
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
class ItemsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController
    private lateinit var sortByAlpha: String
    private lateinit var sortByColor: String
    private lateinit var addItem: String
    private lateinit var iconMenu: String

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            sortByAlpha = stringResource(id = R.string.sort_by_alpha)
            sortByColor = stringResource(id = R.string.sort_by_color)
            addItem = stringResource(id = R.string.add_item)
            iconMenu = stringResource(id = R.string.icon_menu)
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
    fun allButtons_areClickable() {
        composeRule.onNodeWithContentDescription(sortByAlpha).assertHasClickAction()
        composeRule.onNodeWithContentDescription(sortByColor).assertHasClickAction()
        composeRule.onNodeWithContentDescription(addItem).assertHasClickAction()
        composeRule.onNodeWithContentDescription(iconMenu).assertHasClickAction()
    }
}