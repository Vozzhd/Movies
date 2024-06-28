package com.example.movies.di

import com.example.movies.searchMovies.data.api.MoviesRepositoryImpl
import com.example.movies.searchMovies.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

single <MoviesRepository> { MoviesRepositoryImpl(get(),get()) }

}