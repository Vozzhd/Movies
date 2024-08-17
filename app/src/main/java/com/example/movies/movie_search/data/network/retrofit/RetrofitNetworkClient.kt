package com.example.movies.movie_search.data.network.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.movies.details.data.MovieCastRequest
import com.example.movies.details.data.MovieDetailsRequest
import com.example.movies.movie_search.data.dto.MoviesSearchRequest
import com.example.movies.movie_search.data.dto.Response
import com.example.movies.movie_search.data.network.IMDbApiService
import com.example.movies.movie_search.data.network.NetworkClient
import com.example.movies.person_search.data.dto.NamesSearchRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RetrofitNetworkClient(
    private val context: Context,
    private val imdbService: IMDbApiService
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        if ((dto !is MoviesSearchRequest)
            && (dto !is MovieDetailsRequest)
            && (dto !is MovieCastRequest)
            && (dto !is NamesSearchRequest)
        ) {
            return Response().apply { resultCode = 400 }
        }

        return withContext(Dispatchers.IO) {
            try {
                val response = when (dto) {
                    is MoviesSearchRequest -> imdbService.searchMovies(dto.expression)
                    is MovieDetailsRequest -> imdbService.getMovieDetails(dto.movieId)
                    is NamesSearchRequest -> imdbService.searchNames(dto.expression)
                    else -> imdbService.getFullCast((dto as MovieCastRequest).movieId)
                }
                response.apply { resultCode = 200 }

            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }


        private fun isConnected(): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
            return false
        }
    }

