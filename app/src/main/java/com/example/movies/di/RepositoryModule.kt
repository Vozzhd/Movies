package com.example.movies.di

import com.example.movies.details.data.MovieCastConverter
import com.example.movies.movie_search.data.converters.MovieDbConvertor
import com.example.movies.movie_search.data.impl.HistoryRepositoryImpl
import com.example.movies.movie_search.data.impl.MoviesRepositoryImpl
import com.example.movies.movie_search.domain.api.MoviesRepository
import com.example.movies.movie_search.domain.db.HistoryRepository
import com.example.movies.person_search.data.repository.NamesRepositoryImpl
import com.example.movies.person_search.domain.api.NamesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { MovieCastConverter() }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get(), get(),get(),get()) }
    single<NamesRepository> { NamesRepositoryImpl(get()) }

    factory { MovieDbConvertor() }
    single<HistoryRepository> { HistoryRepositoryImpl(get(), get()) }

}