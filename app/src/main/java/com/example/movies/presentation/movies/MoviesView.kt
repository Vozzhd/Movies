package com.example.movies.presentation.movies
import com.example.movies.ui.movies.models.MoviesState
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MoviesView : MvpView{

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun render(state:MoviesState)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToastMessage(toastMessage: String)

}