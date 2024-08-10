package com.example.movies.person_search.domain.api

import com.example.movies.person_search.domain.Person
import kotlinx.coroutines.flow.Flow

interface NamesInteractor {

    fun searchNames(expression: String) : Flow<Pair<List<Person>?, String?>>

    interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
} 