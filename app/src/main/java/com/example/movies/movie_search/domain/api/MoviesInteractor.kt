package com.example.movies.movie_search.domain.api

import com.example.movies.details.data.MovieDetails
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.movie_search.domain.model.MovieCast
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {
    fun searchMovies(expression: String) : Flow<Pair<List<Movie>?, String?>>
    fun getMoviesDetails(movieId: String) : Flow<Pair<MovieDetails?, String?>>
    fun getMovieCast(movieId : String) : Flow<Pair<MovieCast?,String?>>

    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}