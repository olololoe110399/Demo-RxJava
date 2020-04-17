package com.sun_asterisk.moviedb_50.data.source

import androidx.databinding.ObservableArrayList
import com.sun_asterisk.moviedb_50.data.model.Category
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.data.source.remote.OnDataLoadedCallback
import com.sun_asterisk.moviedb_50.data.source.remote.response.GenresResponse
import com.sun_asterisk.moviedb_50.data.source.remote.response.MoviesResponse
import io.reactivex.Flowable
import io.reactivex.Observable

interface MovieDataSource {
    /**
     * Local
     */
    interface Local {
        fun getCategories(): ObservableArrayList<Category>
        fun getFavorites(): Flowable<MutableList<Favorite>>
        fun addFavorite(favorite: Favorite)
        fun deleteFavorite(movieID: String)
        fun findFavoriteId(movieID: String):Flowable<Int>
    }

    /**
     * Remote
     */
    interface Remote {
        fun getGenres(): Observable<GenresResponse>

        fun getMovies(
            type: String,
            query: String,
            page: Int,
            listener: OnDataLoadedCallback<MoviesResponse>
        )

        fun getMovies(
            type: String,
            query: String,
            page: Int
        ): Observable<MoviesResponse>

        fun getMovieDetails(
            movieID: Int
        ): Observable<Movie>
    }
}
