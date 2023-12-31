package com.adorastudios.movieapp.data.remote.retrofit

import com.adorastudios.movieapp.data.remote.retrofit.response.ConfigurationResponse
import com.adorastudios.movieapp.data.remote.retrofit.response.GenresResponse
import com.adorastudios.movieapp.data.remote.retrofit.response.MovieCastResponse
import com.adorastudios.movieapp.data.remote.retrofit.response.MovieDetailsResponse
import com.adorastudios.movieapp.data.remote.retrofit.response.SearchMovieResponse
import com.adorastudios.movieapp.data.remote.retrofit.response.UpComingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    // get configuration (for images)
    @GET("configuration")
    suspend fun loadConfiguration(): ConfigurationResponse

    // get genres
    @GET("genre/movie/list")
    suspend fun loadGenres(): GenresResponse

    // get movies (previews)
    @GET("movie/upcoming")
    suspend fun loadUpcoming(
        @Query("page") page: Int,
    ): UpComingResponse

    // get movie (details)
    @GET("movie/{movie_id}")
    suspend fun loadMovieDetails(
        @Path("movie_id") movieId: Long,
    ): MovieDetailsResponse

    // get movie cast
    @GET("movie/{movie_id}/credits")
    suspend fun loadMovieCast(
        @Path("movie_id") movieId: Long,
    ): MovieCastResponse

    // get movies (search)
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1,
    ): SearchMovieResponse
}
