package com.adorastudios.movieapp.presentation.screens.details_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adorastudios.movieapp.data.Failure
import com.adorastudios.movieapp.data.Result
import com.adorastudios.movieapp.data.Success
import com.adorastudios.movieapp.domain.MovieDetails
import com.adorastudios.movieapp.domain.MovieRepository
import com.adorastudios.movieapp.presentation.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository,
): ViewModel() {
    private var _state: MutableState<DetailsState> = mutableStateOf(DetailsState())
    var state: State<DetailsState> = _state

    var id: Long = -1

    init {
        savedStateHandle.get<Long>(Screen.PARAM_ID)?.let { param ->
            if (param == -1L) return@let
            this.id = param
            viewModelScope.launch {
                val m = repository.loadMovieRemote(id)
                handleResult(id, m)
            }
        }
    }

    private fun handleResult(movieId: Long, result: Result<MovieDetails?>) {
        when (result) {
            is Success -> if (result.data == null) {
                loadMoviesFromDB(movieId)
            } else {
                _state.value = _state.value.copy(
                    movieDetailsState = MovieDetailsState.MovieLoaded(result.data),
                )
            }

            is Failure -> loadMoviesFromDB(movieId)
        }
    }

    private fun loadMoviesFromDB(movieId: Long) {
        viewModelScope.launch {
            handleMoviePreviewLocaleResult(repository.loadMovieLocale(movieId))
        }
    }

    private fun handleMoviePreviewLocaleResult(result: Result<MovieDetails>) {
        when (result) {
            is Success -> {
                _state.value = _state.value.copy(
                    movieDetailsState = MovieDetailsState.MovieLoaded(result.data),
                )
            }

            is Failure -> {
                _state.value = _state.value.copy(
                    movieDetailsState = MovieDetailsState.MovieError(result.exception),
                )
            }
        }
    }
}
