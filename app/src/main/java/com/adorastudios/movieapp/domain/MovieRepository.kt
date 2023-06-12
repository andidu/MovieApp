package com.adorastudios.movieapp.domain

import com.adorastudios.movieapp.data.Result

interface MovieRepository {
    suspend fun loadMoviesRemote(): Result<List<MoviePreview>>
    suspend fun loadMovieRemote(movieId: Long): Result<MovieDetails>

    suspend fun loadMoviesLocale(): Result<List<MoviePreview>>
    suspend fun loadMovieLocale(movieId: Long): Result<MovieDetails>
    suspend fun searchMovies(query: String): List<MoviePreview>
}
