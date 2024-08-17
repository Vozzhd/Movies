package com.example.movies.movie_search.domain.api

import com.example.movies.details.data.MovieDetails
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.movie_search.domain.model.MovieCast
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun searchMovies(expression: String): Flow<Resource<List<Movie>>>
    fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>>
    fun getMovieCast(movieId: String) : Flow<Resource <MovieCast>>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}