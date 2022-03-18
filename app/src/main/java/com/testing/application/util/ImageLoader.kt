package com.testing.application.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.testing.application.R

object ImageLoader {
    fun loadImage(view: ImageView, url: String, placeholder: Int = R.drawable.baseline) {
        Glide.with(view)
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .fallback(placeholder)
            .into(view)
    }
}