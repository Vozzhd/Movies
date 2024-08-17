package com.example.movies.movie_search.domain.impl

import com.example.movies.movie_search.domain.api.HistoryInteractor
import com.example.movies.movie_search.domain.db.HistoryRepository
import com.example.movies.movie_search.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl (private val historyRepository: HistoryRepository) : HistoryInteractor {
    override fun historyMovies(): Flow<List<Movie>> {
        return historyRepository.historyMovies()
    }
}