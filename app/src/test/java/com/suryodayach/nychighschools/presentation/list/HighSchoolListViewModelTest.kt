package com.suryodayach.nychighschools.presentation.list

import com.suryodayach.nychighschools.data.NYCRepository
import com.suryodayach.nychighschools.data.model.HighSchool
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

class HighSchoolListViewModelTest {

    private lateinit var nycRepository: NYCRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: HighSchoolListViewModel

    @Before
    fun setUp() {
        nycRepository = mockk(relaxed = true)
        viewModel = HighSchoolListViewModel(nycRepository, testDispatcher)
    }

    @Test
    fun `Should set UI state successfully`() = runTest {
        coEvery { nycRepository.getHighSchools() } returns flowOf(fakeHighSchools)

        viewModel.uiState.value.shouldBeTypeOf<HighSchoolListUiState.Loading>()
        viewModel.getHighSchools()

        val successItem = viewModel.uiState.value
        successItem.shouldBeTypeOf<HighSchoolListUiState.Success>()
        successItem.highSchools.shouldBe(fakeHighSchools)
    }

    @Test
    fun `Should set UI state to error when exception occurs`() = runTest {
        val exception = Exception("Error getting high schools")
        coEvery { nycRepository.getHighSchools() } throws exception

        viewModel.uiState.value.shouldBeTypeOf<HighSchoolListUiState.Loading>()
        viewModel.getHighSchools()

        val errorItem = viewModel.uiState.value
        errorItem.shouldBeTypeOf<HighSchoolListUiState.Error>()
        errorItem.error.shouldBe(exception.message)
    }

    companion object {
        val fakeHighSchools = listOf(
            HighSchool(dbn = "111", schoolName = "School 1"),
            HighSchool(dbn = "222", schoolName = "School 2")
        )
    }
}