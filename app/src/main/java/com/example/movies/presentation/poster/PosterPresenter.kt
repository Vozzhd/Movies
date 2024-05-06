package com.example.movies.presentation.poster

class PosterPresenter(
    private val activity: PosterView,
    private val imageUrl: String
) {
    fun onCreate() {
        activity.setPosterImage(imageUrl)
    }
}