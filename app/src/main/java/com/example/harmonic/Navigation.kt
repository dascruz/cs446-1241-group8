package com.example.harmonic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.harmonic.Destinations.WELCOME_ROUTE

object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val TRACKING = "tracking"
    const val INSIGHTS = "insights"
}

@Composable
fun HarmonicNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = WELCOME_ROUTE
    ) {
    }
}