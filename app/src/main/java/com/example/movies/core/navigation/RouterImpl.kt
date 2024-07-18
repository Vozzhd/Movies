package com.example.movies.core.navigation

import androidx.fragment.app.Fragment

class RouterImpl : Router {
    override val navigationHolder: NavigationHolder = NavigationHolderImpl()

    override fun openFragment(fragment: Fragment) {
        navigationHolder.openFragment(fragment)
    }
}