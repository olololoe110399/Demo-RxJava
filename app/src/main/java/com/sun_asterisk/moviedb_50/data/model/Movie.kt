package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val movieID: Int,
    @SerializedName("title")
    val movieTitle: String,
    @SerializedName("overview")
    val movieOverView: String,
    @SerializedName("poster_path")
    val moviePosterPath: String,
    @SerializedName("backdrop_path")
    val movieBackdropPath: String,
    @SerializedName("vote_average")
    val movieVoteAverage: Double,
    @SerializedName("release_date")
    val movieReleaseDate: String,
    @SerializedName("genres")
    val genres: List<Genres>,
    @SerializedName("production_companies")
    val produce: List<Produce>,
    @SerializedName("credits")
    val casts: Casts,
    @SerializedName("videos")
    val trailers: MovieTrailers
)
