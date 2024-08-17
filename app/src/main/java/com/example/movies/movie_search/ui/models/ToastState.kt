package com.example.movies.movie_search.ui.models

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}