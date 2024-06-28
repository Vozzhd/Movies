package com.example.movies.di

import com.example.movies.searchMovies.domain.api.MoviesInteractor
import com.example.movies.searchMovies.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single <MoviesInteractor> { MoviesInteractorImpl(get()) }
}