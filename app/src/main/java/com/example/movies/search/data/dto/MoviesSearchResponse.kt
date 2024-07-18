package com.example.movies.search.data.dto

class MoviesSearchResponse(val searchType: String,
                           val expression: String,
                           val results: List<MovieDto>) : Response()