package com.experiment.feature.launch.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun splashScreen_holdsContent_untilReady() {
        // Arrange
        val expectedText = "Hello Android!"

        // Act
        composeTestRule.waitUntil(timeoutMillis = 5000L) {
            composeTestRule
                .onAllNodesWithText(expectedText)
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Assert
        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
    }
}
