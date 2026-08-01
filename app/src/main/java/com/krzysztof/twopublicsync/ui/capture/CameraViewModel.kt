package com.krzysztof.twopublicsync.ui.capture

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel(
    private val repository: CameraRepository = CameraRepository()
) : ViewModel() {

    fun handleCapturedPhoto(bitmap: Bitmap) {
        viewModelScope.launch {

            // 1. Zapisz bitmapę do pliku
            val file: File = repository.savePhoto(bitmap)

            // 2. Wyślij plik na backend (placeholder)
            val uploadSuccess = repository.uploadPhoto(file)

            // 3. Analiza gestu na podstawie pliku (placeholder)
            val gestureCorrect = repository.analyzeGesture(file)

            // TODO: możesz tu wywołać GameViewModel, ScoreViewModel itd.
        }
    }
}
