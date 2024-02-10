package com.suryodayach.nychighschools.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suryodayach.nychighschools.data.NYCRepository
import com.suryodayach.nychighschools.data.model.HighSchool
import com.suryodayach.nychighschools.di.MainDispatcher
import com.suryodayach.nychighschools.navigation.HIGH_SCHOOL_DBN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighSchoolDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val nycRepository: NYCRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<HighSchoolDetailsUiState>(HighSchoolDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _uiState.value = HighSchoolDetailsUiState.Error(exception.message)
    }

    init {
        val highSchoolDbn = savedStateHandle.get<String>(HIGH_SCHOOL_DBN).orEmpty()
        getHighSchool(highSchoolDbn)
    }

    private fun getHighSchool(dbn: String) {
        viewModelScope.launch(errorHandler + dispatcher) {
            nycRepository.getHighSchool(dbn).collect { highSchool ->
                _uiState.value = HighSchoolDetailsUiState.Success(highSchool)
            }
        }
    }
}

sealed interface HighSchoolDetailsUiState {
    data object Loading : HighSchoolDetailsUiState
    data class Success(val highSchool: HighSchool) : HighSchoolDetailsUiState
    data class Error(val error: String?) : HighSchoolDetailsUiState
}