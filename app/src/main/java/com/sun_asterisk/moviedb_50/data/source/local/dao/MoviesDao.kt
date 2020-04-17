package com.sun_asterisk.moviedb_50.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sun_asterisk.moviedb_50.data.model.Favorite
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface MoviesDao {
    @Query("select * from tbl_movie")
    fun getAllFavorites(): Flowable<MutableList<Favorite>>

    @Insert(onConflict = REPLACE)
    fun addFavorite(vararg favorite: Favorite)

    @Delete
    fun deleteFavorite(vararg favorite: Favorite)

    @Query("select count(*) from tbl_movie where movie_id = :movieId")
    fun findFavorite(movieId: String): Maybe<Int>
}
