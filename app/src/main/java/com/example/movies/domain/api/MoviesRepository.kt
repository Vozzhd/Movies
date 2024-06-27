package com.example.movies.domain.api

import com.example.movies.domain.models.Movie
import com.example.movies.domain.models.MovieDetails
import com.example.movies.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun getMovieDetails(movieId: String): Resource<MovieDetails>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}