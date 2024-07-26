package com.example.movies.person_search.domain.api

import com.example.movies.person_search.domain.Person

interface NamesInteractor {

    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
} 