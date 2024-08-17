package com.example.movies.person_search.domain.api

import com.example.movies.person_search.domain.Person
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {
    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}