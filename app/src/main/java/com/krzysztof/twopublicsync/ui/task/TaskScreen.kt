package com.krzysztof.twopublicsync.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.krzysztof.twopublicsync.core.model.TaskState

@Composable
fun TaskScreen(
    title: String,
    description: String,
    icon: ImageVector,
    state: TaskState,
    onAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        when (state) {
            TaskState.WaitingForPartner -> {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Czekasz na drugą osobę…")
            }

            TaskState.Ready -> {
                Button(onClick = onAction) {
                    Text("Wykonaj gest")
                }
            }

            TaskState.Completed -> {
                Text("Zadanie wykonane! 🎉")
            }
        }
    }
}
