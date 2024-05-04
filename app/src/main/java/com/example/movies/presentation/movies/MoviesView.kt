package com.example.movies.presentation.movies

import com.example.movies.domain.models.Movie
import com.example.movies.ui.movies.models.MoviesState

interface MoviesView {

    fun render(state:MoviesState)
    fun showToastMessage(toastMessage: String)

}