package com.adorastudios.movieapp.data.remote

import com.adorastudios.movieapp.domain.Actor
import com.adorastudios.movieapp.domain.Genre
import com.adorastudios.movieapp.domain.MovieDetails
import com.adorastudios.movieapp.domain.MoviePreview

interface RemoteDataSource {
    suspend fun loadMovies(): List<MoviePreview>
    suspend fun loadMovie(id: Long): MovieDetails
    suspend fun loadGenres(): List<Genre>
    suspend fun loadActors(id: Long): List<Actor>
    suspend fun searchMovies(query: String, page: Int = 1): List<MoviePreview>
}
