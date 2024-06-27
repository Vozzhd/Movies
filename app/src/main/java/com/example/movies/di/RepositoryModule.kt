package com.example.movies.di

import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

single <MoviesRepository> { MoviesRepositoryImpl(get(),get()) }

}