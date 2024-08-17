package com.example.movies.movie_search.data.network

import com.example.movies.movie_search.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}