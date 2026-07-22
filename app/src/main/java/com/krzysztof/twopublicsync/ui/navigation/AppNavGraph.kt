package com.krzysztof.twopublicsync.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krzysztof.twopublicsync.ui.home.HomeScreen
import com.krzysztof.twopublicsync.ui.onboarding.OnboardingScreen
import com.krzysztof.twopublicsync.ui.task.SyncGestureScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {

        composable("onboarding") {
            OnboardingScreen(
                onContinue = {
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onTaskSelected = { taskId ->
                    navController.navigate("task/$taskId")
                }
            )
        }

        composable("task/sync_gesture") {
            SyncGestureScreen()
        }
    }
}
