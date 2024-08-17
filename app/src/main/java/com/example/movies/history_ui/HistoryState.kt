package com.example.movies.history_ui

import com.example.movies.movie_search.domain.model.Movie


sealed interface HistoryState {

    object Loading : HistoryState

    data class Content(
        val movies: List<Movie>
    ) : HistoryState

    data class Empty(
        val message: String
    ) : HistoryState
}