package com.krzysztof.twopublicsync.ui.capture

import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class CameraRepository {

    fun savePhoto(bitmap: Bitmap): File {
        val dir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "twopublicsync"
        )
        if (!dir.exists()) dir.mkdirs()

        val file = File(dir, "photo_${System.currentTimeMillis()}.jpg")

        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }

        return file
    }

    suspend fun uploadPhoto(file: File): Boolean {
        // TODO: implementacja API (Retrofit, multipart)
        // Na razie zwracamy true jako placeholder
        return true
    }

    suspend fun analyzeGesture(file: File): Boolean {
        // TODO: wywołanie backendu / ML
        // Na razie zwracamy true jako placeholder
        return true
    }
}
