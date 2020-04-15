package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class MovieTrailers(
    @SerializedName("results")
    val data: List<MovieTrailer>
)
