package com.example.movies.presentation.movies

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.util.Creator
import com.example.movies.R
import com.example.movies.domain.api.MoviesInteractor
import com.example.movies.domain.models.Movie
import com.example.movies.ui.movies.MoviesAdapter
import com.example.movies.ui.movies.models.MoviesState

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
) {
    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())


    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {

        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }
        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.render(
                MoviesState(
                    movies = movies,
                    isLoading = true,
                    errorMessage = null
                )
            )
            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {

                        handler.post {
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                            }
                            when {
                                errorMessage != null -> {
                                    view.render(
                                        MoviesState(
                                            movies = emptyList(),
                                            isLoading = false,
                                            errorMessage = context.getString(R.string.something_went_wrong)
                                        )
                                    )
                                }

                                movies.isEmpty() -> {
                                    view.render(
                                        MoviesState(
                                            movies = emptyList(),
                                            isLoading = false,
                                            errorMessage = (context.getString(R.string.nothing_found))
                                        )
                                    )
                                }

                                else -> {
                                    view.render(
                                        MoviesState(
                                            movies = movies,
                                            isLoading = false,
                                            errorMessage = null
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