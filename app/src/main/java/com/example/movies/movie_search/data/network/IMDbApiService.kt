package com.example.movies.movie_search.data.network

import com.example.movies.details.data.MovieDetailsResponse
import com.example.movies.details.data.dto.MovieCastResponse
import com.example.movies.movie_search.data.dto.MoviesSearchResponse
import com.example.movies.person_search.data.dto.NamesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    suspend fun searchMovies(@Path("expression") expression: String): MoviesSearchResponse

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetailsResponse

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    suspend fun getFullCast(@Path("movie_id") movieId: String): MovieCastResponse

    @GET("/en/API/SearchName/k_zcuw1ytf/{expression}")
   suspend fun searchNames(@Path("expression") expression: String): NamesSearchResponse
}