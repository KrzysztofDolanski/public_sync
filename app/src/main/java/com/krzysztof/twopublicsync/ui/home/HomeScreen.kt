package com.krzysztof.twopublicsync.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onTaskSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Wybierz zadanie")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { onTaskSelected("sync_gesture") }) {
            Text("Gest synchronizacji")
        }
    }
}
