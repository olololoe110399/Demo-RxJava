package com.sun_asterisk.moviedb_50.data.model

import android.database.Cursor
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sun_asterisk.moviedb_50.screen.base.BaseFilterAdapter
@Entity(tableName = "tbl_movie")
data class Favorite(
    @ColumnInfo(name = "movie_id")
    @PrimaryKey var movieID: String,
    @ColumnInfo(name = "movie_title") var movieTitle: String,
    @ColumnInfo(name = "movie_overview") var movieOverView: String,
    @ColumnInfo(name = "movie_poster_path") var moviePosterPath: ByteArray?,
    @ColumnInfo(name = "movie_vote_average") var movieVoteAverage: String,
    @ColumnInfo(name = "movie_release_date") var movieReleaseDate: String
) : BaseFilterAdapter.Searchable {
    override fun getSearchCriteria(): String {
        return movieTitle
    }

    constructor(cursor: Cursor) : this(
        movieID = cursor.getString(cursor.getColumnIndex(MovieEntry.ID)),
        movieTitle = cursor.getString(cursor.getColumnIndex(MovieEntry.TITLE)),
        movieOverView = cursor.getString(cursor.getColumnIndex(MovieEntry.OVERVIEW)),
        moviePosterPath = cursor.getBlob(cursor.getColumnIndex(MovieEntry.POSTER_PATH)),
        movieVoteAverage = cursor.getString(cursor.getColumnIndex(MovieEntry.VOTE_AVERAGE)),
        movieReleaseDate = cursor.getString(cursor.getColumnIndex(MovieEntry.RELEASE_DATE))
    )

    constructor(movie: Movie, byteArray: ByteArray?) : this(
        movieID = movie.movieID.toString(),
        movieTitle = movie.movieTitle,
        movieOverView = movie.movieOverView,
        moviePosterPath = byteArray,
        movieVoteAverage = movie.movieVoteAverage.toString(),
        movieReleaseDate = movie.movieReleaseDate
    )

    object MovieEntry {
        const val FAVORITE = "tbl_favorite_movie"
        const val ID = "movie_id"
        const val TITLE = "movie_title"
        const val OVERVIEW = "movie_overview"
        const val POSTER_PATH = "movie_poster_path"
        const val VOTE_AVERAGE = "movie_vote_average"
        const val RELEASE_DATE = "movie_release_date"
        const val SELECTION_LIKE_ID = "movie_id = ?"
        const val SQL_FAVORITE =
            "CREATE TABLE $FAVORITE (" +
                    "$ID text primary key, " +
                    "$TITLE text, " +
                    "$OVERVIEW text, " +
                    "$POSTER_PATH BLOB, " +
                    "$VOTE_AVERAGE text, " +
                    "$RELEASE_DATE text);"
    }
}