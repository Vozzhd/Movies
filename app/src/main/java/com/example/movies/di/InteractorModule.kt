package com.example.movies.di

import com.example.movies.movie_search.domain.api.MoviesInteractor
import com.example.movies.movie_search.domain.impl.MoviesInteractorImpl
import com.example.movies.person_search.data.repository.NamesInteractorImpl
import com.example.movies.person_search.domain.api.NamesInteractor
import org.koin.dsl.module

val interactorModule = module {
    single<MoviesInteractor> { MoviesInteractorImpl(get()) }
    single<NamesInteractor> { NamesInteractorImpl(get()) }
}