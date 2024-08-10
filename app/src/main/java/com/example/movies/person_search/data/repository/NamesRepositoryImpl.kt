package com.example.movies.person_search.data.repository

import com.example.movies.movie_search.data.network.NetworkClient
import com.example.movies.person_search.data.dto.NamesSearchRequest
import com.example.movies.person_search.data.dto.NamesSearchResponse
import com.example.movies.person_search.domain.Person
import com.example.movies.person_search.domain.api.NamesRepository
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow{
        val response = networkClient.doRequest(NamesSearchRequest(expression))

         when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as NamesSearchResponse) {
                   val data = results.map {
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}