package com.example.movies.details.ui.cast

import com.example.movies.details.ui.cast.RV.MoviesCastRVItem

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<MoviesCastRVItem>
    ) : MoviesCastState

    data class Error(
        val message: String
    ) : MoviesCastState
}