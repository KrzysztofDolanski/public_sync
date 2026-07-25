package com.krzysztof.twopublicsync.ui.capture

import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class CameraRepository {

    fun savePhoto(bitmap: Bitmap) {
        val dir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "twopublicsync"
        )
        if (!dir.exists()) dir.mkdirs()

        val file = File(dir, "photo_${System.currentTimeMillis()}.jpg")

        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
    }

    suspend fun uploadPhoto(bitmap: Bitmap) {
        // TODO: implementacja API
        // Retrofit multipart upload
    }
}
