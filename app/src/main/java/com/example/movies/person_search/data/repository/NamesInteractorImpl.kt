package com.example.movies.person_search.data.repository

import com.example.movies.person_search.domain.Person
import com.example.movies.person_search.domain.api.NamesInteractor
import com.example.movies.person_search.domain.api.NamesRepository
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NamesInteractorImpl(private val repository: NamesRepository) : NamesInteractor {

    override fun searchNames(expression: String) : Flow<Pair<List<Person>?, String?>> {

        return repository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data,null)
                }
                is Resource.Error -> {
                    Pair(null,result.message)
                }
            }
        }
    }
}