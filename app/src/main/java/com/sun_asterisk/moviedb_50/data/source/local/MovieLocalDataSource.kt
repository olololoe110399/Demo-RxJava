package com.sun_asterisk.moviedb_50.data.source.local

import androidx.databinding.ObservableArrayList
import com.sun_asterisk.moviedb_50.R
import com.sun_asterisk.moviedb_50.data.model.Category
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.source.MovieDataSource
import com.sun_asterisk.moviedb_50.data.source.local.dao.MoviesDao
import io.reactivex.Flowable

class MovieLocalDataSource private constructor(private val favoritesDao: MoviesDao) :
    MovieDataSource.Local {

    override fun getCategories(): ObservableArrayList<Category> {
        val list = ObservableArrayList<Category>()
        list.addAll(
            listOf(
                Category(Category.CategoryEntry.NOW_PLAYING, R.drawable.now_playing),
                Category(Category.CategoryEntry.UPCOMING, R.drawable.upcoming),
                Category(Category.CategoryEntry.TOP_RATE, R.drawable.toprated),
                Category(Category.CategoryEntry.POPULAR, R.drawable.popular)
            )
        )
        return list
    }

    override fun getFavorites(): Flowable<MutableList<Favorite>> {
        return favoritesDao.getAllFavorites()
    }

    override fun addFavorite(favorite: Favorite) {
        return favoritesDao.addFavorite(favorite)
    }

    override fun deleteFavorite(movieID: String) {
        favoritesDao.deleteFavorite(movieID)
    }

    override fun findFavoriteId(movieID: String): Flowable<Int> {
        return favoritesDao.findFavorite(movieID)
    }

    companion object {
        private var instance: MovieLocalDataSource? = null
        fun getInstance(favoritesDao: MoviesDao) =
            instance ?: MovieLocalDataSource(favoritesDao).also { instance = it }
    }
}
