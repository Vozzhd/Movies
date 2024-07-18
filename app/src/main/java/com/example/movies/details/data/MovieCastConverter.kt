package com.example.movies.details.data

import com.example.movies.details.data.dto.ActorResponse
import com.example.movies.details.data.dto.CastItemResponse
import com.example.movies.details.data.dto.DirectorsResponse
import com.example.movies.details.data.dto.MovieCastResponse
import com.example.movies.details.data.dto.OtherResponse
import com.example.movies.details.data.dto.WritersResponse
import com.example.movies.search.domain.model.MovieCast
import com.example.movies.search.domain.model.MovieCastPerson

class MovieCastConverter {

    fun convert(response: MovieCastResponse): MovieCast {
        return with(response) {
            MovieCast(
                imdbId = this.imDbId,
                fullTitle = this.fullTitle,
                directors = convertDirectors(this.directors),
                others = convertOthers(this.others),
                writers = convertWriters(this.writers),
                actors = convertActors(this.actors)
            )
        }
    }


    private fun convertDirectors(directorsResponse: DirectorsResponse): List<MovieCastPerson> {
        return directorsResponse.items.map { it.toMovieCastPerson() }
    }

    private fun convertOthers(otherResponse: List<OtherResponse>): List<MovieCastPerson> {
        return otherResponse.flatMap { otherResponse ->
            otherResponse.items.map { it.toMovieCastPerson() }
        }
    }

    private fun convertWriters(writersResponse: WritersResponse): List<MovieCastPerson> {
        return writersResponse.items.map { it.toMovieCastPerson() }
    }

    private fun convertActors(actorResponse: List<ActorResponse>): List<MovieCastPerson> {
        return actorResponse.map { actor ->
            MovieCastPerson(
                id = actor.id,
                name = actor.name,
                description = actor.asCharacter,
                image = actor.image,
            )
        }
    }


    private fun CastItemResponse.toMovieCastPerson(jobPrefix: String = ""): MovieCastPerson {
        return MovieCastPerson(
            id = this.id,
            name = this.name,
            description = if (jobPrefix.isEmpty()) this.description else "$jobPrefix -- ${this.description}",
            image = null,
        )
    }

}