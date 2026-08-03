package com.krzysztof.twopublicsync.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krzysztof.twopublicsync.game.GameViewModel
import com.krzysztof.twopublicsync.score.ScoreViewModel
import com.krzysztof.twopublicsync.user.UserViewModel
import com.krzysztof.twopublicsync.ui.capture.CaptureUserPhotoScreen
import com.krzysztof.twopublicsync.ui.home.HomeScreen
import com.krzysztof.twopublicsync.ui.result.ResultScreen
import com.krzysztof.twopublicsync.ui.task.SyncGestureScreen
import com.krzysztof.twopublicsync.user.UserAvatarScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val scoreViewModel: ScoreViewModel = viewModel()
    val gameViewModel: GameViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                navController = navController,
                scoreViewModel = scoreViewModel,
                gameViewModel = gameViewModel,
                userViewModel = userViewModel
            )
        }

        composable("task_sync") {
            SyncGestureScreen(navController, gameViewModel)
        }

        composable("capture_photo") {
            CaptureUserPhotoScreen(
                onPhotoCaptured = { bitmap ->
                    gameViewModel.verifierCapturedPhoto(bitmap)
                    gameViewModel.analyzeGesture(bitmap)
                    navController.navigate("result")
                },
                scoreViewModel = scoreViewModel
            )
        }

        composable("result") {
            val correct = gameViewModel.state.value.gestureCorrect ?: false
            if (correct) scoreViewModel.addPoint()
            ResultScreen(navController, correct)
        }

        composable("user_avatar") {
            UserAvatarScreen(navController, userViewModel)
        }
    }
}
