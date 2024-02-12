package com.suryodayach.nychighschools.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.suryodayach.nychighschools.navigation.schoolListRoute

@Composable
fun rememberNycHighSchoolAppState(
    navController: NavHostController = rememberNavController()
): NycHighSchoolAppState {
    return remember(
        navController
    ) {
        NycHighSchoolAppState(
            navController
        )
    }
}

@Stable
class NycHighSchoolAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val appBarTitle: String
        @Composable
        get() = when (currentDestination?.route) {
            schoolListRoute -> "NYC High Schools"
            else -> "Details"
        }

    fun navigateToPreviousScreen() {
        navController.popBackStack()
    }
}