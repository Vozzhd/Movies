package com.example.movies.ui.poster

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.Creator
import com.example.movies.R

class PosterActivity : Activity() {

    private lateinit var poster: ImageView
    private val posterController = Creator.providePosterController(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}