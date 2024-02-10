package com.suryodayach.nychighschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.suryodayach.nychighschools.ui.NycHighSchoolApp
import com.suryodayach.nychighschools.ui.theme.NYCHighSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCHighSchoolsTheme {
                NycHighSchoolApp(windowSizeClass = calculateWindowSizeClass(activity = this))
            }
        }
    }
}