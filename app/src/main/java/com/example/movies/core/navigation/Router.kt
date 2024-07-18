package com.example.movies.core.navigation

import androidx.fragment.app.Fragment

interface Router {
    val navigationHolder: NavigationHolder
    fun openFragment(fragment : Fragment)
}


/**
 * Router — это входная точка для фрагментов,
 * которые хотят открыть следующий экран.
 */