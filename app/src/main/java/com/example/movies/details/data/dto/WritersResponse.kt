package com.example.movies.details.data.dto

data class WritersResponse(
    val items: List<CastItemResponse>,
    val job: String
)