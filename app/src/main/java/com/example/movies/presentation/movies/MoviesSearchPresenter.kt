package com.example.movies.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.example.movies.R
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.models.Movie
import com.example.movies.ui.movies.models.MoviesState
import com.example.movies.util.Creator

class MoviesSearchPresenter(
    private val context: Context
) {
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var lastSearchText: String? = null
    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

    fun detachView() {
        this.view = null
    }

    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {
        if (lastSearchText == changedText) {
            return
        }
        this.lastSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }
        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime
        )
    }

    private fun renderState(state: MoviesState) {
        this.state = state
        this.view?.render(state)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view?.render(MoviesState.Loading)

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {

                        handler.post {
                            val movies = mutableListOf<Movie>()
                            if (foundMovies != null) {
                                movies.addAll(foundMovies)
                            }
                            when {
                                errorMessage != null -> {
                                    renderState(
                                        MoviesState.Error(
                                            errorMessage = context.getString(R.string.something_went_wrong)
                                        )
                                    )
                                    view?.showToastMessage(errorMessage)
                                }

                                movies.isEmpty() -> {
                                    renderState(
                                        MoviesState.Empty(
                                            message = (context.getString(R.string.nothing_found))
                                        )
                                    )
                                }

                                else -> {
                                    renderState(
                                        MoviesState.Content(
                                            movies = movies
                                        )
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }
}