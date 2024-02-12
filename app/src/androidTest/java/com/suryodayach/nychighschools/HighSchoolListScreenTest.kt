package com.suryodayach.nychighschools

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.suryodayach.nychighschools.utils.Description
import com.suryodayach.nychighschools.presentation.list.HighSchoolListScreen
import com.suryodayach.nychighschools.presentation.list.HighSchoolListUiState
import com.suryodayach.nychighschools.ui.theme.NYCHighSchoolsTheme
import com.suryodayach.nychighschools.utils.DummyContent
import org.junit.Rule
import org.junit.Test

class HighSchoolListScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState_isRendered() {
        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolListScreen(
                    uiState = HighSchoolListUiState.Loading,
                    onItemClick = { _ -> }
                )
            }
        }

        testRule.onNodeWithContentDescription(
            Description.HIGH_SCHOOL_LIST_LOADING
        ).assertIsDisplayed()
    }

    @Test
    fun stateWithHighSchools_isRendered() {
        val highSchoolList = DummyContent.highSchoolList
        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolListScreen(
                    uiState = HighSchoolListUiState.Success(
                        highSchoolList
                    ),
                    onItemClick = { _ -> }
                )
            }
        }

        testRule.onNodeWithText(
            highSchoolList[0].schoolName
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchoolList[0].dbn
        ).assertIsDisplayed()

        testRule.onNodeWithText(
            highSchoolList[0].city
        ).assertIsDisplayed()

        testRule.onNodeWithContentDescription(
            Description.HIGH_SCHOOL_LIST_LOADING
        ).assertDoesNotExist()
    }

    @Test
    fun errorState_isRendered() {
        val errorMessage = "Error Getting High Schools!"
        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolListScreen(
                    uiState = HighSchoolListUiState.Error(
                        errorMessage
                    ),
                    onItemClick = { _ -> }
                )
            }
        }

        testRule.onNodeWithText(
            errorMessage
        ).assertIsDisplayed()

        testRule.onNodeWithContentDescription(
            Description.HIGH_SCHOOL_LIST_LOADING
        ).assertDoesNotExist()
    }


    @Test
    fun stateWithHighSchools_ClickOnItem_isRegistered() {
        val highSchoolList = DummyContent.highSchoolList
        val targetHighSchool = highSchoolList.first()

        testRule.setContent {
            NYCHighSchoolsTheme {
                HighSchoolListScreen(
                    uiState = HighSchoolListUiState.Success(highSchoolList),
                    onItemClick = { dbn ->
                        assert(dbn == targetHighSchool.dbn)
                    }
                )
            }
        }

        testRule.onNodeWithText(targetHighSchool.schoolName)
            .performClick()
    }

}