package com.example.movies.di

import com.example.movies.core.navigation.Router
import com.example.movies.core.navigation.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()
    single<Router> { router }
    single { router.navigationHolder }
}