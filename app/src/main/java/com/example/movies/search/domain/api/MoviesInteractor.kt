package com.example.movies.search.domain.api

import com.example.movies.details.data.MovieDetails
import com.example.movies.search.domain.model.Movie
import com.example.movies.search.domain.model.MovieCast

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)
    fun getMoviesDetails(movieId: String, consumer: MovieDetailsConsumer)
    fun getMovieCast(movieId : String,consumer : MovieCastConsumer)
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