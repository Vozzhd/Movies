package com.example.movies.data

import com.example.movies.data.dto.MoviesSearchRequest
import com.example.movies.data.dto.MoviesSearchResponse
import com.example.movies.data.storage.LocalStorage
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.models.Movie
import com.example.movies.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient,private val localStorage: LocalStorage) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description, inFavorite = stored.contains(it.id))})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}