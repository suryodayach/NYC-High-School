package com.suryodayach.nychighschools.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val schoolListRoute = "school_list_route"
const val schoolDetailsRoute = "school_details_route"
const val HIGH_SCHOOL_DBN = "high_school_dbn"
const val schoolDetailsPattern = "$schoolDetailsRoute/{$HIGH_SCHOOL_DBN}"

fun NavController.navigateToHighSchoolDetailsScreen(
    dbn: String,
    navOptions: NavOptions? = null
) {
    this.navigate("$schoolDetailsRoute/$dbn", navOptions)
}