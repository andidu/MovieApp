package com.adorastudios.movieapp.data.remote.network_module

import com.adorastudios.movieapp.data.remote.retrofit.MovieApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class NetworkModule {
    private val baseUrl = "https://api.themoviedb.org/"
    private val version = "3/"

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor(loggingInterceptor)
        .addInterceptor(ApiKeyInterceptor())
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl + version)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(httpClient)

    private val retrofit = retrofitBuilder.build()

    val api: MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}
