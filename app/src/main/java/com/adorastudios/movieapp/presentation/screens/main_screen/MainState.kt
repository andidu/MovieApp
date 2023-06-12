package com.adorastudios.movieapp.presentation.screens.main_screen

data class MainState(
    val moviesResult: MoviesResult = MoviesResult.EmptyQuery,
    val searchState: SearchState = SearchState.Ready,
)
