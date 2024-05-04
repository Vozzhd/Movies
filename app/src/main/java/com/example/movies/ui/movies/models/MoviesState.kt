package com.example.movies.ui.movies.models

import com.example.movies.domain.models.Movie

data class MoviesState(
    val movies: List<Movie>,
    val isLoading: Boolean,
    val errorMessage: String?
) {
}