package com.example.movies.person_search.data.repository

import com.example.movies.movie_search.data.network.retrofit.NetworkClient
import com.example.movies.person_search.data.dto.NamesSearchRequest
import com.example.movies.person_search.data.dto.NamesSearchResponse
import com.example.movies.person_search.domain.Person
import com.example.movies.person_search.domain.api.NamesRepository
import com.example.movies.util.Resource

 class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as NamesSearchResponse) {
                    Resource.Success(results.map {
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    })
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}