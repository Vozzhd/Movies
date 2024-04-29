package com.example.movies.data

import com.example.movies.data.dto.MoviesSearchRequest
import com.example.movies.data.dto.MoviesSearchResponse
import com.example.movies.domain.api.MoviesRepository
import com.example.movies.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): List<Movie> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        if (response.resultCode == 200) {
            return (response as MoviesSearchResponse).results.map {
                Movie(it.id, it.resultType, it.image, it.title, it.description) }
        } else {
            return emptyList()
        }
    }
}