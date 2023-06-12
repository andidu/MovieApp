package com.adorastudios.movieapp.presentation.screens.main_screen

sealed class MainEvent {
    data class SearchValueChange(val value: String): MainEvent()
}
