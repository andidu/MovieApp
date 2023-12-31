package com.adorastudios.movieapp.data.locale

import com.adorastudios.movieapp.domain.MovieDetails
import com.adorastudios.movieapp.domain.MoviePreview

interface LocaleDataSource {
    suspend fun loadMovies(): List<MoviePreview>
    suspend fun insertMovies(moviePreviewFromNetwork: List<MoviePreview>)
    suspend fun loadMovie(movieId: Long): List<MovieDetails>
    suspend fun insertMovieDetails(movieDetailsFromNetwork: MovieDetails)
}
