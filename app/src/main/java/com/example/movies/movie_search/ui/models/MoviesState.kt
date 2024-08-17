package com.example.movies.movie_search.ui.models

import com.example.movies.movie_search.domain.model.Movie

sealed interface MoviesState {
    object Loading : MoviesState

    data class Content(
        val movies: List<Movie>
    ) : MoviesState

    data class Error(
        val errorMessage: String
    ) : MoviesState

    data class Empty(
        val message: String
    ) : MoviesState
}