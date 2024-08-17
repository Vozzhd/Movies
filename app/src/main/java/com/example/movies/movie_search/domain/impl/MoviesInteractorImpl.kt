package com.example.movies.movie_search.domain.impl

import com.example.movies.details.data.MovieDetails
import com.example.movies.movie_search.domain.api.MoviesInteractor
import com.example.movies.movie_search.domain.api.MoviesRepository
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.movie_search.domain.model.MovieCast
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    override fun searchMovies(expression: String): Flow<Pair<List<Movie>?, String?>> {

        return repository.searchMovies(expression).map { result ->
            when(result) {
                is Resource.Success -> Pair(result.data,null)
                is Resource.Error -> Pair(null,result.message)
            }
        }
    }

    override fun getMoviesDetails(movieId: String): Flow<Pair<MovieDetails?,String?>>{
       return repository.getMovieDetails(movieId).map { result ->
           when(result) {
               is Resource.Success -> Pair(result.data,null)
               is Resource.Error -> Pair(null,result.message)
           }
       }
    }

    override fun getMovieCast(movieId: String): Flow<Pair<MovieCast?, String?>> {
        return repository.getMovieCast(movieId).map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.message)
            }
        }
    }


    override fun addMovieToFavorites(movie: Movie) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        repository.removeMovieFromFavorites(movie)
    }


}