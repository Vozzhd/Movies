package com.example.movies.di

import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single <MoviesInteractor> { MoviesInteractorImpl(get()) }
}