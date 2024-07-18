package com.example.movies.core.navigation

import androidx.fragment.app.Fragment

interface NavigationHolder {
    fun attachNavigator(navigator: Navigator)
    fun detachNavigator()
    fun openFragment(fragment: Fragment)
}

/**
 * Сущность для хранения ссылки на Navigator.
 */