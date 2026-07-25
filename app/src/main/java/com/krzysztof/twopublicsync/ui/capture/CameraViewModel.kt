package com.krzysztof.twopublicsync.ui.capture

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CameraViewModel(
    private val repository: CameraRepository = CameraRepository()
) : ViewModel() {

    fun handleCapturedPhoto(bitmap: Bitmap) {
        viewModelScope.launch {
            repository.savePhoto(bitmap)
            repository.uploadPhoto(bitmap)
        }
    }
}
