package com.example.movies.history_ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.movie_search.domain.model.Movie

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder = HistoryViewHolder(parent)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int = movies.size
}