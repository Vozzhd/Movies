package com.example.movies.domain.api

import com.example.movies.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}