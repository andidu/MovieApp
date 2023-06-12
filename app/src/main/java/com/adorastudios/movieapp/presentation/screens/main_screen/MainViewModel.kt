@file:OptIn(FlowPreview::class)

package com.adorastudios.movieapp.presentation.screens.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adorastudios.movieapp.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,
): ViewModel() {
    private var _searchValue: MutableState<String> = mutableStateOf("")
    var searchValue: State<String> = _searchValue

    private var _state: MutableState<MainState> = mutableStateOf(MainState())
    var state: State<MainState> = _state

    private val flow = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            flow.debounce(100).collect {
                if (it.isNotEmpty()) startSearch(it)
            }
        }
    }

    private fun startSearch(query: String) {
        if (state.value.searchState == SearchState.Loading) return
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.searchMovies(query)
            }
            _state.value = _state.value.copy(
                searchState = SearchState.Ready,
                moviesResult = MoviesResult.ValidResult(movies),
            )
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SearchValueChange -> {
                _searchValue.value = event.value
                viewModelScope.launch {
                    flow.emit(event.value)
                }
            }
        }
    }
}
