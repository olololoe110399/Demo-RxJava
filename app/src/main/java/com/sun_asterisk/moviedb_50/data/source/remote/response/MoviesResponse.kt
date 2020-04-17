package com.sun_asterisk.moviedb_50.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_50.data.model.Movie

data class MoviesResponse(
    @SerializedName("page")
    val moviePage: Int,
    @SerializedName("total_results")
    val movieTotalResult: Int,
    @SerializedName("total_pages")
    val movieTotalPage: Int,
    @SerializedName("results")
    val list: List<Movie>
)
