package com.kcode.previewimage

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

class PreviewImage(private val context: FragmentActivity) {

    private var urls: ArrayList<String>? = null
    private var position: Int = 0

    fun setUrl(urls: ArrayList<String>): PreviewImage {
        this.urls = urls
        return this
    }

    fun setPosition(position: Int): PreviewImage {
        this.position = position
        return this
    }

    fun setImageLoader(imageLoader:ImageLoader):PreviewImage{
        ImageLoaderManager.imageLoader = imageLoader
        return this
    }

    fun start() {
        if (urls == null) {
            return
        }

        PreviewActivity.start(urls!!,position,context)

    }


}