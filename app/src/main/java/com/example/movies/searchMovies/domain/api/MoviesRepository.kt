package com.example.movies.searchMovies.domain.api

import com.example.movies.searchMovies.domain.model.Movie
import com.example.movies.details.data.MovieDetails
import com.example.movies.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun getMovieDetails(movieId: String): Resource<MovieDetails>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}