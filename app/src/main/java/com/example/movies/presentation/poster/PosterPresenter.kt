package com.example.movies.presentation.poster

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.R

class PosterPresenter(
    private val activity: PosterView,
    private val imageUrl: String
) {
    fun onCreate() {
        activity.setPosterImage(imageUrl)
    }
}