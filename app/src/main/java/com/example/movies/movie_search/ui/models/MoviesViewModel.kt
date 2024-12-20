package com.example.movies.movie_search.ui.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.R
import com.example.movies.movie_search.domain.api.MoviesInteractor
import com.example.movies.movie_search.domain.model.Movie
import com.example.movies.util.SingleLiveEvent
import com.example.movies.util.debounce
import kotlinx.coroutines.launch


class MoviesViewModel(
    private val moviesInteractor: MoviesInteractor,
    private val context: Context
) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val stateLiveData = MutableLiveData<MoviesState>()
    fun observeState(): LiveData<MoviesState> = mediatorStateLiveData

    private val showToast = SingleLiveEvent<String>()
    fun observeShowToast(): LiveData<String> = showToast

    private var latestSearchText: String? = null

    private val movieSearchDebounce = debounce<String>(SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { changedText ->
        searchRequest(changedText)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText != changedText) {
            latestSearchText = changedText
            movieSearchDebounce(changedText)
        }
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

            viewModelScope.launch {
                moviesInteractor
                    .searchMovies(newSearchText)
                    .collect{pair ->
                        processResult(pair.first,pair.second)
                    }
            }
        }
    }

    private fun processResult(foundMovies: List<Movie>?, errorMessage: String?) {

        val movies = mutableListOf<Movie>()
        if (foundMovies != null) {
            movies.addAll(foundMovies)
        }

        when {
            errorMessage != null -> { renderState(MoviesState.Error(errorMessage = context.getString(R.string.something_went_wrong)))
                showToast.postValue(errorMessage!!)
            }

            movies.isEmpty() -> { renderState(MoviesState.Empty(message = context.getString(R.string.nothing_found)))
            }

            else -> { renderState(MoviesState.Content(movies = movies))
            }
        }
    }

    private val mediatorStateLiveData = MediatorLiveData<MoviesState>().also { liveData ->
        liveData.addSource(stateLiveData) { movieState ->
            liveData.value = when (movieState) {
//                is MoviesState.Content -> MoviesState.Content(movieState.movies.sortedByDescending { it.inFavorite })
                is MoviesState.Empty -> movieState
                is MoviesState.Error -> movieState
                is MoviesState.Loading -> movieState
                is MoviesState.Content -> movieState
            }
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }

//    fun toggleFavorite(movie: Movie) {
//        if (movie.inFavorite) {
//            moviesInteractor.removeMovieFromFavorites(movie)
//        } else {
//            moviesInteractor.addMovieToFavorites(movie)
//        }
//        updateMovieContent(movie.id, movie.copy(inFavorite = !movie.inFavorite))
//    }
//
//    private fun updateMovieContent(movieId: String, newMovie: Movie) {
//        val currentState = stateLiveData.value
//
//        // 2
//        if (currentState is MoviesState.Content) {
//            // 3
//            val movieIndex = currentState.movies.indexOfFirst { it.id == movieId }
//
//            // 4
//            if (movieIndex != -1) {
//                // 5
//                stateLiveData.value = MoviesState.Content(
//                    currentState.movies.toMutableList().also {
//                        it[movieIndex] = newMovie
//                    }
//                )
//            }
//        }
//    }
}