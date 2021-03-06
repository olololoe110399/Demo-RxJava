package com.sun_asterisk.moviedb_50.screen.search

import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.utils.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(private val movieRepository: MovieRepository) : SearchContract.Presenter {
    private var view: SearchContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun getGenres() {
        val disposable: Disposable = movieRepository.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ genreResponse ->
                view?.onGetGenresSuccess(genreResponse.list)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun getCategories() {
        val disposable: Disposable = movieRepository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                view?.getCategoriesSuccess(list)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun getMovies(type: String, query: String, page: Int) {
        val disposable: Disposable = movieRepository.getMovies(type, query, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view?.onLoading(true)
            }
            .subscribe({ data ->
                view?.onGetMoviesTopRatedSuccess(data.list)
            },
                { throwable -> view?.onError(throwable.message.toString()) })
        compositeDisposable.add(disposable)
    }

    override fun onStart() {
        view?.onLoading(false)
        getGenres()
        getCategories()
        getMovies(Constant.BASE_TOP_RATE)
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: SearchContract.View?) {
        this.view = view
    }
}
