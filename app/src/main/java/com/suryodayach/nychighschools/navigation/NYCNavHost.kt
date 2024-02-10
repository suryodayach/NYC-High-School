package com.suryodayach.nychighschools.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.suryodayach.nychighschools.presentation.details.HighSchoolDetailsRoute
import com.suryodayach.nychighschools.presentation.list.HighSchoolListRoute
import com.suryodayach.nychighschools.ui.NycHighSchoolAppState

@Composable
fun NYCNavHost(
    appState: NycHighSchoolAppState,
    modifier: Modifier = Modifier,
    startDestination: String = schoolListRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = schoolListRoute) {
            HighSchoolListRoute(
                onItemClick = { highSchoolDbn ->
                    navController.navigateToHighSchoolDetailsScreen(highSchoolDbn)
                }
            )
        }
        composable(
            route = schoolDetailsPattern,
            arguments = listOf(
                navArgument(HIGH_SCHOOL_DBN) { type = NavType.StringType }
            )
        ) {
            HighSchoolDetailsRoute()
        }
    }
}