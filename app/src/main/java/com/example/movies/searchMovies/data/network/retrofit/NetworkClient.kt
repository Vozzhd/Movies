package com.example.movies.searchMovies.data.network.retrofit

import com.example.movies.searchMovies.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}