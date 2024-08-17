package com.example.movies.movie_search.data.converters

import com.example.movies.movie_search.data.db.entity.MovieEntity
import com.example.movies.movie_search.data.dto.MovieDto
import com.example.movies.movie_search.domain.model.Movie

class MovieDbConvertor {

    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }
}