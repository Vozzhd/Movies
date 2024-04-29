package com.example.movies.data

import com.example.movies.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}