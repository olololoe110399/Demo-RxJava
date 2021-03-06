package com.sun_asterisk.moviedb_50.screen.favorite

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sun_asterisk.moviedb_50.R
import com.sun_asterisk.moviedb_50.data.model.Favorite
import com.sun_asterisk.moviedb_50.data.repository.MovieRepository
import com.sun_asterisk.moviedb_50.data.source.local.MovieLocalDataSource
import com.sun_asterisk.moviedb_50.data.source.local.MoviesDatabase
import com.sun_asterisk.moviedb_50.data.source.remote.MovieRemoteDataSource
import com.sun_asterisk.moviedb_50.screen.details.MovieDetailsFragment
import com.sun_asterisk.moviedb_50.screen.favorite.adapter.FavoriteAdapter
import com.sun_asterisk.moviedb_50.utils.FavoriteEnum
import com.sun_asterisk.moviedb_50.utils.NetworkUtil
import com.sun_asterisk.moviedb_50.utils.showSnackBar
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment(), FavoriteContract.View {
    private lateinit var presenter: FavoriteContract.Presenter
    var favoriteAdapter: FavoriteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            val movieRepository: MovieRepository =
                MovieRepository.getInstance(
                    MovieRemoteDataSource.getInstance(),
                    MovieLocalDataSource.getInstance(MoviesDatabase.getInstance(it).movieDao())
                )
            presenter = FavoritePresenter(movieRepository)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        Handler().postDelayed({ presenter.getFavorite() }, 500)
    }

    override fun onGetFavoritesSuccess(favorites: MutableList<Favorite>) {
        favoriteAdapter = FavoriteAdapter(favorites)
        view?.run {
            favoriteMovieRecyclerView.setHasFixedSize(true)
            favoriteMovieRecyclerView.adapter = favoriteAdapter?.apply {
                onItemClick = { category, _ ->
                    addFragment(category)
                }
            }?.apply {
                onItemDelete = { favorite, i ->
                    presenter.deleteFavorite(i, favorite)
                }
            }
        }
    }

    override fun updateFavoritesAfterRemovingItem(position: Int) {
        favoriteAdapter?.removeData(position)
    }

    override fun notifyDeleteFavorite(type: FavoriteEnum) {
        if (type == FavoriteEnum.DELETE_FAVORITE_SUCCESS) {
            activity?.showSnackBar(R.string.notification_delete_success)
        } else {
            activity?.showSnackBar(R.string.notification_delete_failed)
        }
    }

    override fun onError(str: String?) {
        str?.let {
            Toast.makeText(activity, it, Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onLoading(isLoad: Boolean) {
        view?.run {
            if (isLoad) {
                favoriteSwipeRefresh.isRefreshing = false
                frameProgressBarMovie.visibility = View.GONE
            } else {
                frameProgressBarMovie.visibility = View.VISIBLE
            }
        }
    }

    private fun addFragment(favorite: Favorite) {
        activity?.let {
            val fragment =
                MovieDetailsFragment.getInstance(favorite.movieID.toInt(), favorite.movieTitle)
            if (NetworkUtil.isConnectedToNetwork(it)) {
                it.supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.mainFrameLayout, fragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                val message = getString(R.string.check_internet_fail)
                Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}