package com.adorastudios.movieapp.presentation.screens.main_screen

sealed class SearchState {
    object Loading: SearchState()
    object Ready: SearchState()
}
