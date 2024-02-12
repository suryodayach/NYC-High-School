package com.suryodayach.nychighschools.presentation.details

import androidx.lifecycle.SavedStateHandle
import com.suryodayach.nychighschools.data.NYCRepository
import com.suryodayach.nychighschools.data.model.HighSchool
import com.suryodayach.nychighschools.navigation.HIGH_SCHOOL_DBN
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HighSchoolDetailsViewModelTest {

    private lateinit var nycRepository: NYCRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: HighSchoolDetailsViewModel

    @Before
    fun setUp() {
        nycRepository = mockk(relaxed = true)
    }

    @Test
    fun `Should set UI state successfully`() = runTest {
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        coEvery { savedStateHandle.get<String>(HIGH_SCHOOL_DBN) } returns "111"
        viewModel = HighSchoolDetailsViewModel(savedStateHandle, nycRepository, testDispatcher)

        val dbn = savedStateHandle.get<String>(HIGH_SCHOOL_DBN).orEmpty()
        coEvery { nycRepository.getHighSchool("111") } returns flowOf(fakeHighSchool)

        viewModel.uiState.value.shouldBeTypeOf<HighSchoolDetailsUiState.Loading>()
        viewModel.getHighSchool(dbn)

        val successItem = viewModel.uiState.value
        successItem.shouldBeTypeOf<HighSchoolDetailsUiState.Success>()
        successItem.highSchool.shouldBe(fakeHighSchool)
    }

    @Test
    fun `Should set UI state to error when exception occurs`() = runTest {
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        coEvery { savedStateHandle.get<String>(HIGH_SCHOOL_DBN) } returns "222"
        viewModel = HighSchoolDetailsViewModel(savedStateHandle, nycRepository, testDispatcher)

        val dbn = savedStateHandle.get<String>(HIGH_SCHOOL_DBN).orEmpty()
        val exception = Exception("Error getting high school details")
        coEvery { nycRepository.getHighSchool("222") } throws exception

        viewModel.uiState.value.shouldBeTypeOf<HighSchoolDetailsUiState.Loading>()
        viewModel.getHighSchool(dbn)

        val errorItem = viewModel.uiState.value
        errorItem.shouldBeTypeOf<HighSchoolDetailsUiState.Error>()
        errorItem.error.shouldBe(exception.message)
    }

    companion object {
        val fakeHighSchool = HighSchool(dbn = "111", schoolName = "School 1")
    }
}