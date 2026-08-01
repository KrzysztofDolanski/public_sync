package com.krzysztof.twopublicsync.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krzysztof.twopublicsync.game.GameViewModel
import com.krzysztof.twopublicsync.game.Task
import com.krzysztof.twopublicsync.score.ScoreViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    scoreViewModel: ScoreViewModel,
    gameViewModel: GameViewModel
) {
    val score = scoreViewModel.score.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(260.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )

                Divider()

                NavigationDrawerItem(
                    label = { Text("Twoje punkty: ${score.value}") },
                    selected = false,
                    onClick = { }
                )

                NavigationDrawerItem(
                    label = { Text("Gest synchronizacji") },
                    selected = false,
                    onClick = {
                        gameViewModel.startTask(Task("sync", "Gest synchronizacji", 1))
                        navController.navigate("task_sync")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Zrób zdjęcie użytkownika") },
                    selected = false,
                    onClick = {
                        navController.navigate("capture_photo")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("TwoPublicSync") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
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
    }
}
