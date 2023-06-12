package com.adorastudios.movieapp.domain

data class MovieDetails(
    val id: Long,
    val title: String,
    val storyLine: String,
    val detailImageUrl: String?,
    val age: Int,
    val genres: List<Genre>,
    val rating: Double,
    val isLiked: Boolean,
    val actors: List<Actor>,
    val reviewCount: Int,
)
