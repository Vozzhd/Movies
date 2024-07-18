package com.example.movies.di

import com.example.movies.details.data.MovieCastConverter
import com.example.movies.search.data.api.MoviesRepositoryImpl
import com.example.movies.search.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { MovieCastConverter() }

    single<MoviesRepository> { MoviesRepositoryImpl(get(), get(), get()) }

}