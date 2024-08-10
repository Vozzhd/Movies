package com.example.movies.person_search.data.dto

import com.example.movies.movie_search.data.dto.Response

class NamesSearchResponse(
    val expression: String,
    val results: List<PersonDto>
) : Response()