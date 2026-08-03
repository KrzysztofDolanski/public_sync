package com.krzysztof.twopublicsync.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krzysztof.twopublicsync.game.GameViewModel
import com.krzysztof.twopublicsync.game.Task
import com.krzysztof.twopublicsync.score.ScoreViewModel
import com.krzysztof.twopublicsync.user.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    scoreViewModel: ScoreViewModel,
    gameViewModel: GameViewModel,
    userViewModel: UserViewModel
) {
    val score = scoreViewModel.score.collectAsState()
    val avatar = userViewModel.avatar.collectAsState()
    val name = userViewModel.name.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Avatar + dane użytkownika
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = avatar.value),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate("user_avatar")
                                scope.launch { drawerState.close() }
                            }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = name.value,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Punkty: ${score.value}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Divider()

                NavigationDrawerItem(
                    label = { Text("Gest synchronizacji") },
                    selected = false,
                    icon = { Icon(Icons.Default.Task, contentDescription = null) },
                    onClick = {
                        gameViewModel.startTask(Task("sync", "Gest synchronizacji", 1))
                        navController.navigate("task_sync")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Zrób zdjęcie użytkownika") },
                    selected = false,
                    icon = { Icon(Icons.Default.CameraAlt, contentDescription = null) },
                    onClick = {
                        navController.navigate("capture_photo")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Ranking") },
                    selected = false,
                    icon = { Icon(Icons.Default.Leaderboard, contentDescription = null) },
                    onClick = {
                        navController.navigate("ranking")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Historia punktów") },
                    selected = false,
                    icon = { Icon(Icons.Default.History, contentDescription = null) },
                    onClick = {
                        navController.navigate("history")
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
