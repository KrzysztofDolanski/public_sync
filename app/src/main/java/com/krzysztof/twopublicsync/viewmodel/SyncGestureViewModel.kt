package com.krzysztof.twopublicsync.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krzysztof.twopublicsync.core.model.TaskState
import com.krzysztof.twopublicsync.data.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SyncGestureViewModel(
    private val repo: TaskRepository = TaskRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(TaskState.WaitingForPartner)
    val state: StateFlow<TaskState> = _state

    init {
        viewModelScope.launch {
            repo.waitForPartner().collect { ready ->
                if (ready) _state.value = TaskState.Ready
            }
        }
    }

    fun performGesture() {
        viewModelScope.launch {
            repo.confirmGesture()
            _state.value = TaskState.Completed
        }
    }
}
