package com.adorastudios.movieapp.data.locale

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adorastudios.movieapp.data.locale.room.ActorDB
import com.adorastudios.movieapp.data.locale.room.ActorMovieDB
import com.adorastudios.movieapp.data.locale.room.GenreDB
import com.adorastudios.movieapp.data.locale.room.GenreMovieDB
import com.adorastudios.movieapp.data.locale.room.MovieDao
import com.adorastudios.movieapp.data.locale.room.MovieDetailsDB
import com.adorastudios.movieapp.data.locale.room.MoviePreviewsDB

@Database(
    entities = [
        MoviePreviewsDB::class, MovieDetailsDB::class, ActorDB::class,
        GenreDB::class, ActorMovieDB::class, GenreMovieDB::class,
    ],
    version = 1,
)
abstract class MovieDatabase : RoomDatabase() {

    companion object {

        fun create(appContext: Context): MovieDatabase =
            Room.databaseBuilder(
                appContext,
                MovieDatabase::class.java,
                MovieDbContract.DATABASE_NAME,
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    abstract fun getMovieDao(): MovieDao
}
