package com.example.movies.person_search.ui.model

import com.example.movies.person_search.domain.Person

sealed interface NamesState {

    object Loading : NamesState

    data class Content(
        val persons: List<Person>
    ) : NamesState

    data class Error(
        val message: String
    ) : NamesState

    data class Empty(
        val message: String
    ) : NamesState

}