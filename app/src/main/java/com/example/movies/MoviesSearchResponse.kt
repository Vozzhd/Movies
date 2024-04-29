package com.example.movies

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<Movie>)