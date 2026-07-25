package com.krzysztof.twopublicsync.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krzysztof.twopublicsync.game.GameViewModel
import com.krzysztof.twopublicsync.game.Task
import com.krzysztof.twopublicsync.score.ScoreViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    scoreViewModel: ScoreViewModel,
    gameViewModel: GameViewModel
) {
    val score = scoreViewModel.score.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Twoje punkty: ${score.value}",
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                gameViewModel.startTask(Task("sync", "Gest synchronizacji", 1))
                navController.navigate("task_sync")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gest synchronizacji")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("capture_photo") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zrób zdjęcie użytkownika")
        }
    }
}
