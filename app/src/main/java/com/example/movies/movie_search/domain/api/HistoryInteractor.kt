package com.example.movies.movie_search.domain.api

import com.example.movies.movie_search.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {

    fun historyMovies(): Flow<List<Movie>>
}