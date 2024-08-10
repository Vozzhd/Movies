package com.example.movies.movie_search.data.network

import com.example.movies.movie_search.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
    suspend fun doRequestSuspended(dto: Any): Response
}