package com.example.rx_mvvm_retrofit.viewmodal

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable

class MoviesCardViewItemViewModal(val name: String?, private val bitmap: Bitmap, private val resources: Resources) {

    fun getBitmap(): BitmapDrawable {
        return BitmapDrawable(resources, bitmap)
    }

}