package com.example.movies.movie_search.domain.api

import com.example.movies.details.data.MovieDetails
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.movie_search.domain.model.MovieCast
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {
    fun searchMovies(expression: String) : Flow<Pair<List<Movie>?, String?>>
    fun getMoviesDetails(movieId: String) : Flow<Pair<List<MovieDetails>?, String?>>
    fun getMovieCast(movieId : String) : Flow<Pair<MovieCast?,String?>>

    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }
    interface MovieDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }
    interface MovieCastConsumer {
        fun consume(movieCast: MovieCast?,errorMessage: String?)
    }
}