package com.example.movies.details.ui.cast.RV

import com.example.movies.searchMovies.domain.model.MovieCastPerson

sealed interface    MoviesCastRVItem {
    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem


    data class PersonItem (
        val data: MovieCastPerson
    ) : MoviesCastRVItem
}