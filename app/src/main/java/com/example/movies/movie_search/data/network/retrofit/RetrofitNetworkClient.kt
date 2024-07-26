package com.example.movies.movie_search.data.network.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.movies.details.data.MovieCastRequest
import com.example.movies.details.data.MovieDetailsRequest
import com.example.movies.movie_search.data.dto.MoviesSearchRequest
import com.example.movies.movie_search.data.dto.Response
import com.example.movies.movie_search.data.network.IMDbApiService
import com.example.movies.person_search.data.dto.NamesSearchRequest


class RetrofitNetworkClient(
    private val context: Context,
    private val imdbService: IMDbApiService
) : NetworkClient {

    override fun doRequest(dto: Any): Response {
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


        val response = when (dto) {
            is MoviesSearchRequest -> imdbService.searchMovies(dto.expression).execute()
            is MovieDetailsRequest -> imdbService.getMovieDetails(dto.movieId).execute()
            is NamesSearchRequest -> imdbService.searchNames(dto.expression).execute()
            else -> imdbService.getFullCast((dto as MovieCastRequest).movieId).execute()
        }

        val body = response.body()

        return if (body != null) {
            body.apply { resultCode = response.code() }
        } else {
            Response().apply { resultCode = response.code() }
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
