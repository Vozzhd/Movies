package com.example.movies.search.data.network.retrofit

import com.example.movies.search.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}