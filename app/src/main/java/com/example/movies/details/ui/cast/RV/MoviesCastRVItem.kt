package com.example.movies.details.ui.cast.RV

import com.example.movies.core.ui.RVItem
import com.example.movies.search.domain.model.MovieCastPerson

sealed interface    MoviesCastRVItem : RVItem {
    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem


    data class PersonItem (
        val data: MovieCastPerson
    ) : MoviesCastRVItem
}