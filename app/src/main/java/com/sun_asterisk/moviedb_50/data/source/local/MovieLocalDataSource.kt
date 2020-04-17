package com.sun_asterisk.moviedb_50.data.source.local

import com.sun_asterisk.moviedb_50.R
import com.sun_asterisk.moviedb_50.data.model.Category
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.source.MovieDataSource
import com.sun_asterisk.moviedb_50.data.source.local.dao.MoviesDao
import io.reactivex.Flowable
import io.reactivex.Maybe

class MovieLocalDataSource private constructor(private val favoritesDao: MoviesDao) :
    MovieDataSource.Local {

    override fun getCategories(): Flowable<List<Category>> {
        return Flowable.just(
            listOf(
                Category(Category.CategoryEntry.NOW_PLAYING, R.drawable.now_playing),
                Category(Category.CategoryEntry.UPCOMING, R.drawable.upcoming),
                Category(Category.CategoryEntry.TOP_RATE, R.drawable.toprated),
                Category(Category.CategoryEntry.POPULAR, R.drawable.popular)
            )
        )
    }

    override fun getFavorites(): Flowable<MutableList<Favorite>> {
        return favoritesDao.getAllFavorites()
    }

    override fun addFavorite(favorite: Favorite) {
        return favoritesDao.addFavorite(favorite)
    }

    override fun deleteFavorite(favorite: Favorite) {
        favoritesDao.deleteFavorite(favorite)
    }

    override fun findFavoriteId(movieID: String): Maybe<Int> {
        return favoritesDao.findFavorite(movieID)
    }

    companion object {
        private var instance: MovieLocalDataSource? = null
        fun getInstance(favoritesDao: MoviesDao) =
            instance ?: MovieLocalDataSource(favoritesDao).also { instance = it }
    }
}
