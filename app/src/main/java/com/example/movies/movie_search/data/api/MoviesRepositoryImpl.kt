package com.example.movies.movie_search.data.api

import com.example.movies.details.data.MovieCastConverter
import com.example.movies.details.data.MovieCastRequest
import com.example.movies.details.data.MovieDetails
import com.example.movies.details.data.MovieDetailsRequest
import com.example.movies.details.data.MovieDetailsResponse
import com.example.movies.details.data.dto.MovieCastResponse
import com.example.movies.movie_search.data.dto.MoviesSearchRequest
import com.example.movies.movie_search.data.dto.MoviesSearchResponse
import com.example.movies.movie_search.data.network.NetworkClient
import com.example.movies.movie_search.data.storage.LocalStorage
import com.example.movies.movie_search.domain.api.MoviesRepository
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.movie_search.domain.model.MovieCast
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
    private val movieCastConverter: MovieCastConverter
) : MoviesRepository {

    override fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))

        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as MoviesSearchResponse) {
                    val stored = localStorage.getSavedFavorites()
                    val data = results.map {
                        Movie(
                            it.id,
                            it.resultType,
                            it.image,
                            it.title,
                            it.description,
                            inFavorite = stored.contains(it.id)
                        )
                    }
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> = flow {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))

        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    val data = (
                            MovieDetails(
                                id, title, imDbRating, year,
                                countries, genres, directors, writers, stars, plot
                            ))
                    emit(Resource.Success(data))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Resource<MovieCast>> = flow {
        val response = networkClient.doRequest(MovieCastRequest(movieId))

        when (response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> {
                with(response as MovieCastResponse) {
                    val data = movieCastConverter.convert(response as MovieCastResponse)
                    emit(Resource.Success(data))
                }
            }

            else -> emit(Resource.Error("Ошибка сервера"))
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}