package com.sun_asterisk.moviedb_50.screen.favorite

import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.utils.FavoriteEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoritePresenter(private val movieRepository: MovieRepository) : FavoriteContract.Presenter {
    private var view: FavoriteContract.View? = null
    override fun getFavorite() {
        view?.onLoading(false)
        val disposable: Disposable = movieRepository.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view?.onLoading(true)
            }
            .subscribe({ data ->
                view?.onGetFavoritesSuccess(data)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        CompositeDisposable().add(disposable)
    }

    override fun deleteFavorite(position: Int, favoriteId: String) {
        view?.onLoading(false)
        movieRepository.deleteFavorite(favoriteId)
        view?.onLoading(false)
        view?.notifyDeleteFavorite(FavoriteEnum.DELETE_FAVORITE_SUCCESS)
        view?.updateFavoritesAfterRemovingItem(position)
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: FavoriteContract.View?) {
        this.view = view
    }
}
