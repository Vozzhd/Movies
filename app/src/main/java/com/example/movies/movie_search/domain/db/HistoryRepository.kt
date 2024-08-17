package com.example.movies.movie_search.domain.db

import com.example.movies.movie_search.domain.model.Movie
import kotlinx.coroutines.flow.Flow

    interface HistoryRepository {

        fun historyMovies(): Flow<List<Movie>>
    }