package com.kcode.previewimage

import android.widget.ImageView

interface ImageLoader {
    fun onImageLoader(url: String, image: ImageView)
}