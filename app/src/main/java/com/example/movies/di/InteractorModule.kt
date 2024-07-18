package com.example.movies.di

import com.example.movies.search.domain.api.MoviesInteractor
import com.example.movies.search.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    factory <MoviesInteractor> { MoviesInteractorImpl(get()) }
}