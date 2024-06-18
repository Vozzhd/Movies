package com.example.movies.domain.models

import android.app.Application
import com.example.movies.presentation.movies.MoviesSearchViewModel

class MoviesApplication : Application() {
    var moviesSearchViewModel : MoviesSearchViewModel? = null
}