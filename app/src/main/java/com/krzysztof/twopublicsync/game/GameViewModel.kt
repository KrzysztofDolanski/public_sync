package com.krzysztof.twopublicsync.game

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krzysztof.twopublicsync.ui.capture.CameraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val cameraRepository: CameraRepository = CameraRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    fun startTask(task: Task) {
        _state.value = GameState(
            currentTask = task,
            performerReady = false,
            verifierReady = false,
            lastPhoto = null,
            gestureCorrect = null
        )
    }

    fun performerReady() {
        _state.value = _state.value.copy(performerReady = true)
    }

    fun verifierReady() {
        _state.value = _state.value.copy(verifierReady = true)
    }

    fun verifierCapturedPhoto(bitmap: Bitmap) {
        _state.value = _state.value.copy(lastPhoto = bitmap)
    }

    fun analyzeGesture(bitmap: Bitmap) {
        viewModelScope.launch {
            val file = cameraRepository.savePhoto(bitmap)

            // TODO: uploadPhoto(file) jeśli potrzebne
            // val uploaded = cameraRepository.uploadPhoto(file)

            val correct = cameraRepository.analyzeGesture(file)

            _state.value = _state.value.copy(
                gestureCorrect = correct
            )
        }
    }
}
