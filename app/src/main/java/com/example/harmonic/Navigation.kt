package com.example.harmonic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harmonic.Destinations.HOME_ROUTE
import com.example.harmonic.Destinations.INSIGHTS
import com.example.harmonic.Destinations.TRACKING
import com.example.harmonic.components.HomeRoute

object Destinations {
    const val HOME_ROUTE = "home"
    const val TRACKING = "tracking"
    const val INSIGHTS = "insights"
}

@Composable
fun HarmonicNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        composable(HOME_ROUTE) {
            HomeRoute(
                onGoToTracking = { navController.navigate(TRACKING) },
                onGoToInsights = { navController.navigate(INSIGHTS) }
            )
        }
    }
}