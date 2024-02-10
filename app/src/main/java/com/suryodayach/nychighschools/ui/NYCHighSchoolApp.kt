package com.suryodayach.nychighschools.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.suryodayach.nychighschools.ui.component.NYCTopAppBar
import com.suryodayach.nychighschools.navigation.NYCNavHost
import com.suryodayach.nychighschools.navigation.schoolDetailsPattern
import com.suryodayach.nychighschools.navigation.schoolListRoute

@OptIn(ExperimentalComposeUiApi::class, ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun NycHighSchoolApp(
    windowSizeClass: WindowSizeClass,
    appState: NycHighSchoolAppState = rememberNycHighSchoolAppState(),
) {

    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->

        Row(
            Modifier
                .fillMaxSize()
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            Column(Modifier.fillMaxSize()) {
                when (appState.currentDestination?.route) {
                    schoolListRoute -> {
                        NYCTopAppBar(
                            title = appState.appBarTitle
                        )
                    }
                    schoolDetailsPattern -> {
                        NYCTopAppBar(
                            title = appState.appBarTitle,
                            navigationIcon = Icons.Rounded.ArrowBack,
                            navigationIconContentDescription = "Go Back",
                            onNavigationClick = appState::navigateToPreviousScreen
                        )
                    }
                }

                val startDestination = schoolListRoute
                NYCNavHost(
                    appState = appState,
                    startDestination = startDestination
                )
            }
        }

    }
}