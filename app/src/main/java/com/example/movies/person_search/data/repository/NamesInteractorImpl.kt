package com.example.movies.person_search.data.repository

import com.example.movies.person_search.domain.api.NamesInteractor
import com.example.movies.person_search.domain.api.NamesRepository
import com.example.movies.util.Resource
import java.util.concurrent.Executors

class NamesInteractorImpl(private val repository: NamesRepository) : NamesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute {
            when (val resource = repository.searchNames(expression)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(resource.data, resource.message)
                }
            }
        }
    }
}