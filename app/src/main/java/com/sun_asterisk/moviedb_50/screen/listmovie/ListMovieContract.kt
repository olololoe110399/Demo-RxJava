package com.sun_asterisk.moviedb_50.screen.listmovie

import com.sun_asterisk.moviedb_50.data.model.Movie
import com.sun_asterisk.moviedb_50.screen.base.BasePresenter
import com.sun_asterisk.moviedb_50.utils.Constant

interface ListMovieContract {
    /**
     * View
     */
    interface View {
        fun onGetMoviesSuccess(movies: List<Movie>)
        fun onGetMovieResultPage(movieTotalResult: Int, movieTotalPage: Int)
        fun onError(str: String?)
        fun onLoading(isLoad: Boolean)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View?> {
        fun getMovies(
            type: String,
            query: String = Constant.BASE_QUERY_DEFAULT,
            page: Int = Constant.BASE_PAGE_DEFAULT
        )
    }
}
