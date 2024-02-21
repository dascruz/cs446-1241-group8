package com.example.harmonic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harmonic.Destinations.HOME_ROUTE
import com.example.harmonic.Destinations.INSIGHTS
import com.example.harmonic.Destinations.TRACKING
import com.example.harmonic.Destinations.TRACKING_COUNTER_JOBS
import com.example.harmonic.Destinations.TRACKING_DECIMAL_JOBS
import com.example.harmonic.Destinations.TRACKING_ROUTINE_JOBS
import com.example.harmonic.Destinations.TRACKING_TIMER_JOBS
import com.example.harmonic.components.HomeRoute
import com.example.harmonic.components.TrackingRoute

object Destinations {
    const val HOME_ROUTE = "home"
    const val TRACKING = "tracking"
    const val TRACKING_TIMER_JOBS = "tracking/timer_jobs"
    const val TRACKING_ROUTINE_JOBS = "tracking/routing_jobs"
    const val TRACKING_COUNTER_JOBS = "tracking/counter_jobs"
    const val TRACKING_DECIMAL_JOBS = "tracking/decimal_jobs"
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
        composable(TRACKING) {
            TrackingRoute(
                onGoToTimerJob = { navController.navigate(TRACKING_TIMER_JOBS) },
                onGoToRoutineJob = { navController.navigate(TRACKING_ROUTINE_JOBS) },
                onGoToCounterJob = { navController.navigate(TRACKING_COUNTER_JOBS) },
                onGoToDecimalJob = { navController.navigate(TRACKING_DECIMAL_JOBS) }
            )
        }
    }
}