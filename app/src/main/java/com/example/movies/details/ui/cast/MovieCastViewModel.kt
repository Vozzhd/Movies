package com.example.movies.details.ui.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.details.ui.cast.RV.MoviesCastRVItem
import com.example.movies.movie_search.domain.api.MoviesInteractor
import com.example.movies.movie_search.domain.model.MovieCast
import kotlinx.coroutines.launch

class MovieCastViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MoviesCastState>()
    fun observeState(): LiveData<MoviesCastState> = stateLiveData

    init {
        stateLiveData.postValue(MoviesCastState.Loading)

        viewModelScope.launch {
            moviesInteractor.getMovieCast(movieId)
                .collect { pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    private fun processResult(movieCast: MovieCast?, errorMessage: String?) {
        if (movieCast != null) {
            stateLiveData.postValue(castToUiStateContent(movieCast))
        } else {
            stateLiveData.postValue(
                MoviesCastState.Error(
                    errorMessage ?: "Неизвестная ошибка"
                )
            )
        }
    }

    private fun castToUiStateContent(cast: MovieCast): MoviesCastState {
        val items = buildList<MoviesCastRVItem> {
            if (cast.directors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Directors")
                this += cast.directors.map { MoviesCastRVItem.PersonItem(it) }
            }
            if (cast.writers.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Writers")
                this += cast.writers.map { MoviesCastRVItem.PersonItem(it) }
            }
            if (cast.actors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Actors")
                this += cast.actors.map { MoviesCastRVItem.PersonItem(it) }
            }
            if (cast.others.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Others")
                this += cast.others.map { MoviesCastRVItem.PersonItem(it) }
            }
        }
        return MoviesCastState.Content(
            fullTitle = cast.fullTitle,
            items = items
        )
    }
}