package com.adorastudios.movieapp.presentation.screens.details_screen

import com.adorastudios.movieapp.domain.MovieDetails

sealed class MovieDetailsState {
    class MovieLoaded(val movie: MovieDetails): MovieDetailsState()
    object MovieNotLoaded: MovieDetailsState()
    class MovieError(val error: Throwable): MovieDetailsState()
}
