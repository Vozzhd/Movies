package com.example.movies.movie_search.domain.model

data class MovieCast(
    val imdbId: String,
    val fullTitle: String,
    val directors: List<MovieCastPerson>,
    val writers: List<MovieCastPerson>,
    val actors: List<MovieCastPerson>,
    val others: List<MovieCastPerson>,
)
