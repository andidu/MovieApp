package com.adorastudios.movieapp.data.remote.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SearchMovieResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MoviePreviewResponse>,
)
