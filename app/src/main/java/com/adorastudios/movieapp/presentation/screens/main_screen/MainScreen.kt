@file:OptIn(ExperimentalMaterial3Api::class)

package com.adorastudios.movieapp.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adorastudios.movieapp.R
import com.adorastudios.movieapp.presentation.utils.Screen

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val localFocusManager = LocalFocusManager.current

    val state = viewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.searchValue.value,
                onValueChange = { value: String ->
                    viewModel.onEvent(MainEvent.SearchValueChange(value))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    localFocusManager.clearFocus()
                }),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(MainEvent.SearchValueChange(""))
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = stringResource(id = R.string.eraseAll),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                },
            )
        }
        if (state.moviesResult is MoviesResult.ValidResult) {
            LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
                columns = GridCells.Adaptive(150.dp),
            ) {
                items(
                    items = state.moviesResult.result,
                    key = { it.id },
                ) { moviePreview ->
                    MoviePreviewTile(
                        moviePreview = moviePreview,
                    ) {
                        navController.navigate(Screen.detailsScreen(moviePreview.id))
                    }
                }
            }
        }
    }
}
