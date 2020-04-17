package com.sun_asterisk.moviedb_50.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.source.local.dao.MoviesDao

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
    companion object {
        private var instance: MoviesDatabase? = null
        fun getInstance(context: Context) =
            instance ?: Room.databaseBuilder(context, MoviesDatabase::class.java, "database-movies")
                .fallbackToDestructiveMigration()
                .build().also { instance = it }
    }
}
