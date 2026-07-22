package com.krzysztof.twopublicsync.ui.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krzysztof.twopublicsync.viewmodel.SyncGestureViewModel


@Composable
fun SyncGestureScreen(
    viewModel: SyncGestureViewModel = viewModel()
) {
    val state = viewModel.state.value

    TaskScreen(
        title = "Gest synchronizacji",
        description = "Gdy poczujesz wibrację, spójrz wkoło. Druga osoba też tu jest.",
        icon = Icons.AutoMirrored.Default.ArrowForward,
        state = state,
        onAction = { viewModel.performGesture() }
    )
}
