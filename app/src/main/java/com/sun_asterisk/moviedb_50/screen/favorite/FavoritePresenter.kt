package com.sun_asterisk.moviedb_50.screen.favorite

import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.utils.FavoriteEnum
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoritePresenter(private val movieRepository: MovieRepository) : FavoriteContract.Presenter {
    private var view: FavoriteContract.View? = null
    private val compositeDisposable = CompositeDisposable()
    override fun getFavorite() {
        val disposable: Disposable = movieRepository.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                view?.onGetFavoritesSuccess(data)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun deleteFavorite(position: Int, favorite: Favorite) {
        compositeDisposable.add(Observable.fromCallable { movieRepository.deleteFavorite(favorite) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view?.onLoading(true)
                view?.run {
                    notifyDeleteFavorite(FavoriteEnum.DELETE_FAVORITE_SUCCESS)
                    updateFavoritesAfterRemovingItem(position)
                }
            }
            .subscribe())
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
