package com.example.movies.searchMovies.ui.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.searchMovies.domain.model.Movie

class MoviesAdapter(private val clickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent, clickListener)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener { clickListener.onMovieClick(movies.get(position)) }
    }

    override fun getItemCount(): Int = movies.size

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
        fun onFavoriteToggleClick(movie: Movie)
    }
}