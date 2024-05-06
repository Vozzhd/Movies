package com.example.movies.domain.models

import android.app.Application
import com.example.movies.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {
    var moviesSearchPresenter : MoviesSearchPresenter? = null
}