package com.suryodayach.nychighschools.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.suryodayach.nychighschools.data.model.HighSchool
import com.suryodayach.nychighschools.utils.Description
import com.suryodayach.nychighschools.ui.component.HighSchoolItem
import com.suryodayach.nychighschools.utils.DummyContent

@Composable
fun HighSchoolListRoute(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
    viewModel: HighSchoolListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HighSchoolListScreen(
        modifier = modifier,
        uiState = uiState,
        onItemClick = onItemClick
    )
}

@Composable
internal fun HighSchoolListScreen(
    modifier: Modifier = Modifier,
    uiState: HighSchoolListUiState,
    onItemClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .then(modifier)
    ) {
        when (uiState) {
            is HighSchoolListUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .semantics { contentDescription = Description.HIGH_SCHOOL_LIST_LOADING }
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }

            is HighSchoolListUiState.Error -> {
                Text(
                    uiState.error ?: "Error getting High Schools!",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }

            is HighSchoolListUiState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(uiState.highSchools) { highSchool ->
                        HighSchoolItem(
                            highSchool = highSchool,
                            modifier = Modifier
                                .clickable {
                                    onItemClick(highSchool.dbn)
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HighSchoolListScreenPreview() {
    HighSchoolListScreen(
        uiState = HighSchoolListUiState.Success(
            highSchools = DummyContent.highSchoolList
        ),
        onItemClick = { _ -> }
    )
}