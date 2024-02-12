package com.suryodayach.nychighschools.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suryodayach.nychighschools.data.NYCRepository
import com.suryodayach.nychighschools.data.model.HighSchool
import com.suryodayach.nychighschools.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighSchoolListViewModel @Inject constructor(
    private val nycRepository: NYCRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HighSchoolListUiState>(HighSchoolListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _uiState.value = HighSchoolListUiState.Error(exception.message)
    }

    init {
        getHighSchools()
    }

    fun getHighSchools() {
        viewModelScope.launch(errorHandler + dispatcher) {
            nycRepository.getHighSchools().collect { highSchools ->
                _uiState.value = HighSchoolListUiState.Success(highSchools)
            }
        }
    }
}

sealed interface HighSchoolListUiState {
    data object Loading : HighSchoolListUiState
    data class Success(val highSchools: List<HighSchool>) : HighSchoolListUiState
    data class Error(val error: String?) : HighSchoolListUiState
}