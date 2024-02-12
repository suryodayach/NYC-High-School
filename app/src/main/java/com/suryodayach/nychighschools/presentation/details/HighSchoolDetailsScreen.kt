package com.suryodayach.nychighschools.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.suryodayach.nychighschools.utils.DummyContent

@Composable
fun HighSchoolDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: HighSchoolDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HighSchoolDetailsScreen(
        modifier = modifier,
        uiState = uiState,
    )
}

@Composable
internal fun HighSchoolDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: HighSchoolDetailsUiState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .then(modifier)
    ) {
        when (uiState) {
            is HighSchoolDetailsUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .semantics { contentDescription = Description.HIGH_SCHOOL_DETAILS_LOADING }
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }

            is HighSchoolDetailsUiState.Error -> {
                Text(
                    uiState.error ?: "Error getting High School Details!",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }

            is HighSchoolDetailsUiState.Success -> {
                Column(
                ) {
                    Text(
                        uiState.highSchool.schoolName,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        uiState.highSchool.city,
                        style = MaterialTheme.typography.labelMedium
                            .copy(color = MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        uiState.highSchool.overview, style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Address", style = MaterialTheme.typography.titleMedium
                    )
                    Text(uiState.highSchool.location, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Contacts", style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Phone,
                            contentDescription = "Phone Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            uiState.highSchool.phoneNumber ?: "NA",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Email,
                            contentDescription = "Email Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            uiState.highSchool.schoolEmail ?: "NA",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HighSchoolDetailsScreenPreview() {
    HighSchoolDetailsScreen(
        uiState = HighSchoolDetailsUiState.Success(
            highSchool = DummyContent.highSchoolList.first()
        )
    )
}