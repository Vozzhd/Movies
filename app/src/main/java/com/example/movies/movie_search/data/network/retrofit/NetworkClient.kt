package com.example.movies.movie_search.data.network.retrofit

import com.example.movies.movie_search.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}