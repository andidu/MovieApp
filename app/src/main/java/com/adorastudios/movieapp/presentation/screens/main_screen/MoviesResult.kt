package com.adorastudios.movieapp.presentation.screens.main_screen

import com.adorastudios.movieapp.domain.MoviePreview

sealed class MoviesResult {
    data class ValidResult(val result: List<MoviePreview>): MoviesResult()
    object EmptyResult: MoviesResult()
    object EmptyQuery: MoviesResult()
    data class ErrorResult(val e: Throwable): MoviesResult()
    object TerminalError: MoviesResult()
    object Ignore: MoviesResult()
}
