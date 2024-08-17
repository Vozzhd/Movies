package com.example.movies.movie_search.data.impl

import com.example.movies.movie_search.data.converters.MovieDbConvertor
import com.example.movies.movie_search.data.db.AppDatabase
import com.example.movies.movie_search.data.db.entity.MovieEntity
import com.example.movies.movie_search.domain.db.HistoryRepository
import com.example.movies.movie_search.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor
) : HistoryRepository {

    override fun historyMovies(): Flow<List<Movie>> = flow {
        val movies = appDatabase.movieDao().getMovies()
        emit(convertFromMovieEntity(movies))
    }

    private fun convertFromMovieEntity(movies: List<MovieEntity>): List<Movie> {
        return movies.map { movie -> movieDbConvertor.map(movie) }
    }
}