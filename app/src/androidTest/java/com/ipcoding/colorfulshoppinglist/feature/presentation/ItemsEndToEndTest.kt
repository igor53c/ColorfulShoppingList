package com.ipcoding.colorfulshoppinglist.feature.presentation

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
import com.ipcoding.colorfulshoppinglist.core.util.TestTags
import com.ipcoding.colorfulshoppinglist.di.AppModule
import com.ipcoding.colorfulshoppinglist.feature.presentation.add_edit_item.AddEditItemScreen
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
class ItemsEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController
    private lateinit var sortByAlpha: String
    private lateinit var sortByColor: String
    private lateinit var addItem: String
    private lateinit var iconMenu: String
    private lateinit var save: String
    private lateinit var withoutImage: String
    private lateinit var deleteItem: String
    private lateinit var yes: String

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            sortByAlpha = stringResource(id = R.string.sort_by_alpha)
            sortByColor = stringResource(id = R.string.sort_by_color)
            addItem = stringResource(id = R.string.add_item)
            iconMenu = stringResource(id = R.string.icon_menu)
            save = stringResource(id = R.string.save)
            withoutImage = stringResource(id = R.string.without_image)
            deleteItem = stringResource(id = R.string.delete_item)
            yes = stringResource(id = R.string.yes)
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
    fun saveNewItemAndDelete() {

        val testTitle = "test-title"

        composeRule.onNodeWithContentDescription(addItem).performClick()

        composeRule
            .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput(testTitle)

        composeRule.onNodeWithContentDescription(save).performClick()

        composeRule.onNodeWithText(testTitle).assertIsDisplayed()

        composeRule.onNodeWithContentDescription(deleteItem).performClick()

        composeRule.onNodeWithText(yes).performClick()

        composeRule.onNodeWithText(testTitle).assertDoesNotExist()
    }

    @Test
    fun saveNewItems_orderByTitleDescending() {
        for(i in 1..3) {
            composeRule.onNodeWithContentDescription(addItem).performClick()

            composeRule
                .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
                .performTextInput(i.toString())
            composeRule.onNodeWithContentDescription(save).performClick()
        }

        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription(sortByAlpha)
            .performClick()
            .performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[2]
            .assertTextContains("1")

        composeRule.onNodeWithContentDescription(iconMenu).performClick()
        composeRule.onNodeWithText(withoutImage).performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[0].assertDoesNotExist()
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[1].assertDoesNotExist()
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[2].assertDoesNotExist()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITHOUT_IMAGE)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITHOUT_IMAGE)[1]
            .assertTextContains("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITHOUT_IMAGE)[2]
            .assertTextContains("1")
    }

    @Test
    fun saveNewItems_orderByColor() {
        for(i in 1..3) {
            composeRule.onNodeWithContentDescription(addItem).performClick()

            composeRule
                .onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
                .performTextInput(i.toString())
            composeRule.onNodeWithContentDescription(save).performClick()
        }

        composeRule.onNodeWithText("1").assertIsDisplayed()
        composeRule.onNodeWithText("2").assertIsDisplayed()
        composeRule.onNodeWithText("3").assertIsDisplayed()

        composeRule.onNodeWithText("3").performClick()

        composeRule
            .onNodeWithContentDescription(sortByColor)
            .performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[1]
            .assertTextContains("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[2]
            .assertTextContains("2")

        composeRule.onNodeWithContentDescription(iconMenu).performClick()
        composeRule.onNodeWithText(withoutImage).performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[0].assertDoesNotExist()
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[1].assertDoesNotExist()
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITH_IMAGE)[2].assertDoesNotExist()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITHOUT_IMAGE)[0]
            .assertTextContains("3")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITHOUT_IMAGE)[1]
            .assertTextContains("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM_WITHOUT_IMAGE)[2]
            .assertTextContains("2")
    }
}