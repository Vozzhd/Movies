package com.example.movies.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.core.navigation.NavigationHolder
import com.example.movies.core.navigation.NavigatorImpl
import com.example.movies.databinding.ActivityRootBinding
import com.example.movies.search.ui.MoviesFragment
import org.koin.android.ext.android.inject

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding
    private val navigationHolder: NavigationHolder by inject()
    private val navigator = NavigatorImpl(
        R.id.rootFragmentContainerView, supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Привязываем вёрстку к экрану
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigator.openFragment(MoviesFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.attachNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.detachNavigator()
    }
}