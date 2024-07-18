package com.example.movies.search.ui.models

sealed interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}