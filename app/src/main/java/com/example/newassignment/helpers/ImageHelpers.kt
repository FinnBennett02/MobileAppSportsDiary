package com.example.newassignment.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.newassignment.R

fun showImagePicker(intentLauncher: ActivityResultLauncher<Intent>) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        type = "image/*"
        addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        )
    }

    val chooser = Intent.createChooser(intent, "Select Diary Image")
    intentLauncher.launch(chooser)
}

