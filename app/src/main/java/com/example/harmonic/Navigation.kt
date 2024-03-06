package com.example.harmonic

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harmonic.Destinations.HOME_ROUTE
import com.example.harmonic.Destinations.INSIGHTS
import com.example.harmonic.Destinations.TRACKING
import com.example.harmonic.Destinations.TRACKING_ACTIVE_COUNTER_INSTANCE
import com.example.harmonic.Destinations.TRACKING_ACTIVE_DECIMAL_INSTANCE
import com.example.harmonic.Destinations.TRACKING_ACTIVE_ROUTINE_INSTANCE
import com.example.harmonic.Destinations.TRACKING_ACTIVE_TIMER_INSTANCE
import com.example.harmonic.Destinations.TRACKING_COUNTER_INSTANCES
import com.example.harmonic.Destinations.TRACKING_COUNTER_JOBS
import com.example.harmonic.Destinations.TRACKING_DECIMAL_INSTANCES
import com.example.harmonic.Destinations.TRACKING_DECIMAL_JOBS
import com.example.harmonic.Destinations.TRACKING_NEW_COUNTER_INSTANCE
import com.example.harmonic.Destinations.TRACKING_NEW_COUNTER_JOB
import com.example.harmonic.Destinations.TRACKING_NEW_DECIMAL_INSTANCE
import com.example.harmonic.Destinations.TRACKING_NEW_DECIMAL_JOB
import com.example.harmonic.Destinations.TRACKING_NEW_ROUTINE_INSTANCE
import com.example.harmonic.Destinations.TRACKING_NEW_ROUTINE_JOB
import com.example.harmonic.Destinations.TRACKING_NEW_TIMER_INSTANCE
import com.example.harmonic.Destinations.TRACKING_NEW_TIMER_JOB
import com.example.harmonic.Destinations.TRACKING_ROUTINE_INSTANCES
import com.example.harmonic.Destinations.TRACKING_ROUTINE_JOBS
import com.example.harmonic.Destinations.TRACKING_TIMER_INSTANCES
import com.example.harmonic.Destinations.TRACKING_TIMER_JOBS
import com.example.harmonic.Destinations.VIEW_ALL_ACTIVE
import com.example.harmonic.components.TimerInstanceList.TimerInstanceListRoute
import com.example.harmonic.components.create_new_timer_job.CreateNewTimerJobRoute
import com.example.harmonic.components.home.HomeRoute
import com.example.harmonic.components.timer_job_list.TimerJobListRoute
import com.example.harmonic.components.tracking.TrackingRoute
import com.example.harmonic.components.view_all_active.ViewAllActiveRoute


object Destinations {
    const val HOME_ROUTE = "home"
    const val TRACKING = "tracking"
    const val TRACKING_TIMER_JOBS = "tracking/timer_jobs"
    const val TRACKING_ROUTINE_JOBS = "tracking/routing_jobs"
    const val TRACKING_COUNTER_JOBS = "tracking/counter_jobs"
    const val TRACKING_DECIMAL_JOBS = "tracking/decimal_jobs"
    const val TRACKING_NEW_TIMER_JOB = "tracking/new_timer_job"
    const val TRACKING_NEW_ROUTINE_JOB = "tracking/new_routine_job"
    const val TRACKING_NEW_COUNTER_JOB = "tracking/new_counter_job"
    const val TRACKING_NEW_DECIMAL_JOB = "tracking/new_decimal_job"
    const val TRACKING_TIMER_INSTANCES = "tracking/timer_instances/{jobId}"
    const val TRACKING_ROUTINE_INSTANCES = "tracking/routine_instances/{jobId}"
    const val TRACKING_COUNTER_INSTANCES = "tracking/counter_instances/{jobId}"
    const val TRACKING_DECIMAL_INSTANCES = "tracking/decimal_instances/{jobId}"
    const val TRACKING_NEW_TIMER_INSTANCE = "tracking/new_timer_instance"
    const val TRACKING_NEW_ROUTINE_INSTANCE = "tracking/new_routine_instance"
    const val TRACKING_NEW_COUNTER_INSTANCE = "tracking/new_counter_instance"
    const val TRACKING_NEW_DECIMAL_INSTANCE = "tracking/new_decimal_instance"
    const val TRACKING_ACTIVE_TIMER_INSTANCE = "tracking/active_timer_instance/{instanceId}"
    const val TRACKING_ACTIVE_ROUTINE_INSTANCE = "tracking/active_routine_instance/{instanceId}"
    const val TRACKING_ACTIVE_COUNTER_INSTANCE = "tracking/active_counter_instance/{instanceId}"
    const val TRACKING_ACTIVE_DECIMAL_INSTANCE = "tracking/active_decimal_instance/{instanceId}"
    const val INSIGHTS = "insights"
    const val VIEW_ALL_ACTIVE = "view_all_active"
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
                onGoToAllActive = { navController.navigate(VIEW_ALL_ACTIVE)},
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

        composable(TRACKING_TIMER_JOBS) {
            TimerJobListRoute (
                onGoToNewTimer = { navController.navigate(TRACKING_NEW_TIMER_JOB) },
                onNavigateToAllTimerInstance = {navController.navigate("tracking/timer_instances/$it")}
            )
        }

        composable(TRACKING_ROUTINE_JOBS) {}

        composable(TRACKING_COUNTER_JOBS) {}

        composable(TRACKING_DECIMAL_JOBS) {}

        composable(TRACKING_NEW_TIMER_JOB) {
            CreateNewTimerJobRoute (
                onGoToTimerJob = { navController.navigate(TRACKING_TIMER_JOBS) }
            )
        }

        composable(TRACKING_NEW_ROUTINE_JOB) {}

        composable(TRACKING_NEW_COUNTER_JOB) {}

        composable(TRACKING_NEW_DECIMAL_JOB) {}

        composable(TRACKING_TIMER_INSTANCES) {
            val jobIdString = it.arguments?.getString("jobId")
            if (jobIdString != null) {
                TimerInstanceListRoute(
                    job = jobIdString,
                    onNavigateToAllTimerInstance = {navController.navigate(TRACKING_TIMER_INSTANCES)})
            }
        }

        composable(TRACKING_ROUTINE_INSTANCES) {}

        composable(TRACKING_COUNTER_INSTANCES) {}

        composable(TRACKING_DECIMAL_INSTANCES) {}

        composable(TRACKING_NEW_TIMER_INSTANCE) {}

        composable(TRACKING_NEW_ROUTINE_INSTANCE) {}

        composable(TRACKING_NEW_COUNTER_INSTANCE) {}

        composable(TRACKING_NEW_DECIMAL_INSTANCE) {}

        composable(TRACKING_ACTIVE_TIMER_INSTANCE) {}

        composable(TRACKING_ACTIVE_ROUTINE_INSTANCE) {}

        composable(TRACKING_ACTIVE_COUNTER_INSTANCE) {}

        composable(TRACKING_ACTIVE_DECIMAL_INSTANCE) {}

        composable(VIEW_ALL_ACTIVE) {
            ViewAllActiveRoute(
                onNavigateToActiveTimerInstance = { navController.navigate(
                    TRACKING_ACTIVE_TIMER_INSTANCE) },
                onNavigateToActiveRoutineInstance = { navController.navigate(
                    TRACKING_ACTIVE_ROUTINE_INSTANCE) },
                onNavigateToActiveCounterInstance ={ navController.navigate(
                    TRACKING_ACTIVE_TIMER_INSTANCE) },
                onNavigateToActiveDecimalInstance = { navController.navigate(
                    TRACKING_ACTIVE_COUNTER_INSTANCE) },
            )
        }
    }
}