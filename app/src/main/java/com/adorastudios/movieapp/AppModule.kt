package com.adorastudios.movieapp

import android.app.Application
import com.adorastudios.movieapp.data.MovieRepositoryImpl
import com.adorastudios.movieapp.data.locale.LocaleDataSource
import com.adorastudios.movieapp.data.locale.MovieDatabase
import com.adorastudios.movieapp.data.locale.room.RoomDataSource
import com.adorastudios.movieapp.data.remote.RemoteDataSource
import com.adorastudios.movieapp.data.remote.network_module.NetworkModule
import com.adorastudios.movieapp.data.remote.retrofit.RetrofitDataSource
import com.adorastudios.movieapp.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNetworkModule(): NetworkModule {
        return NetworkModule()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(networkModule: NetworkModule): RemoteDataSource {
        return RetrofitDataSource(networkModule.api)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return MovieDatabase.create(app)
    }

    @Provides
    @Singleton
    fun provideLocaleDataSource(movieDatabase: MovieDatabase): LocaleDataSource {
        return RoomDataSource(movieDatabase)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localeDataSource: LocaleDataSource,
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource, localeDataSource)
    }
}
