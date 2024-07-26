package com.example.movies.person_search.domain.api

import com.example.movies.person_search.domain.Person
import com.example.movies.util.Resource

interface NamesRepository {
    fun searchNames(expression: String): Resource<List<Person>>
}