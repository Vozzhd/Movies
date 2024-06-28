package com.example.movies.details.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.details.ui.about.AboutFragment
import com.example.movies.details.ui.poster.PosterFragment

class DetailsViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    private val posterUrl: String, private val movieId: String
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PosterFragment.newInstance(posterUrl)
            else -> AboutFragment.newInstance(movieId)
        }
    }
}