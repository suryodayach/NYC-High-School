package com.suryodayach.nychighschools

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.suryodayach.nychighschools.presentation.details.HighSchoolDetailsScreen
import com.suryodayach.nychighschools.presentation.details.HighSchoolDetailsUiState
import com.suryodayach.nychighschools.ui.theme.NYCHighSchoolsTheme
import com.suryodayach.nychighschools.utils.Description
import com.suryodayach.nychighschools.utils.DummyContent
import org.junit.Rule
import org.junit.Test

class HighSchoolDetailsScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState_isRendered() {
        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolDetailsScreen(
                    uiState = HighSchoolDetailsUiState.Loading
                )
            }
        }

        testRule.onNodeWithContentDescription(
            Description.HIGH_SCHOOL_DETAILS_LOADING
        ).assertIsDisplayed()
    }

    @Test
    fun stateWithHighSchoolDetails_isRendered() {
        val highSchool = DummyContent.highSchoolList.first()
        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolDetailsScreen(
                    uiState = HighSchoolDetailsUiState.Success(
                        highSchool
                    )
                )
            }
        }

        testRule.onNodeWithText(
            highSchool.schoolName
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchool.city
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchool.overview
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchool.location
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchool.phoneNumber ?: "NA"
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchool.schoolEmail ?: "NA"
        ).assertIsDisplayed()

        testRule.onNodeWithContentDescription(
            Description.HIGH_SCHOOL_DETAILS_LOADING
        ).assertDoesNotExist()
    }

    @Test
    fun errorState_isRendered() {
        val errorMessage = "Error Getting High School Details!"
        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolDetailsScreen(
                    uiState = HighSchoolDetailsUiState.Error(
                        errorMessage
                    )
                )
            }
        }

        testRule.onNodeWithText(
            errorMessage
        ).assertIsDisplayed()

        testRule.onNodeWithContentDescription(
            Description.HIGH_SCHOOL_DETAILS_LOADING
        ).assertDoesNotExist()
    }

}