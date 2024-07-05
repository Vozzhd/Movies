package com.example.movies.details.ui.cast

import com.example.movies.core.ui.RVItem

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        val items: List<RVItem>
    ) : MoviesCastState

    data class Error(
        val message: String
    ) : MoviesCastState
}