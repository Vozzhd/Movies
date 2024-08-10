package com.example.movies.di

import android.content.Context
import android.content.SharedPreferences
import com.example.movies.movie_search.data.network.NetworkClient
import com.example.movies.movie_search.data.network.IMDbApiService
import com.example.movies.movie_search.data.network.retrofit.RetrofitNetworkClient
import com.example.movies.movie_search.data.storage.LocalStorage
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl("https://tv-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

    single <SharedPreferences> {
        androidContext().getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single<LocalStorage> { LocalStorage(get()) }

    single<NetworkClient> { RetrofitNetworkClient(androidContext(), get()) }


}