package com.example.movies.details.ui.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.details.ui.cast.RV.MoviesCastRVItem
import com.example.movies.search.domain.api.MoviesInteractor
import com.example.movies.search.domain.model.MovieCast

class MovieCastViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MoviesCastState>()
    fun observeState(): LiveData<MoviesCastState> = stateLiveData

    init {
        stateLiveData.postValue(MoviesCastState.Loading)

        moviesInteractor.getMovieCast(movieId, object : MoviesInteractor.MovieCastConsumer {

            override fun consume(movieCast: MovieCast?, errorMessage: String?) {
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
        })
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