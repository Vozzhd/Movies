package com.example.movies.movie_search.data.api

import com.example.movies.details.data.MovieCastConverter
import com.example.movies.details.data.MovieCastRequest
import com.example.movies.details.data.MovieDetails
import com.example.movies.details.data.MovieDetailsRequest
import com.example.movies.details.data.MovieDetailsResponse
import com.example.movies.details.data.dto.MovieCastResponse
import com.example.movies.movie_search.data.dto.MoviesSearchRequest
import com.example.movies.movie_search.data.dto.MoviesSearchResponse
import com.example.movies.movie_search.data.network.retrofit.NetworkClient
import com.example.movies.movie_search.data.storage.LocalStorage
import com.example.movies.movie_search.domain.api.MoviesRepository
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.movie_search.domain.model.MovieCast
import com.example.movies.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(
                        it.id,
                        it.resultType,
                        it.image,
                        it.title,
                        it.description,
                        inFavorite = stored.contains(it.id)
                    )
                })
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }


    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id, title, imDbRating, year,
                            countries, genres, directors, writers, stars, plot
                        )
                    )
                }
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as MovieCastResponse) {
                    Resource.Success(
                        data = movieCastConverter.convert(response as MovieCastResponse)
                    )
                }
            }
            else -> Resource.Error("Ошибка сервера")
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}