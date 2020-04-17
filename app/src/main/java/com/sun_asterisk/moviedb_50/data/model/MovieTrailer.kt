package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class MovieTrailer(
    @SerializedName( "id")
    val movieTrailerID: String,
    @SerializedName("key")
    val movieTrailerKey: String,
    @SerializedName("name")
    val movieTrailerName: String
)
