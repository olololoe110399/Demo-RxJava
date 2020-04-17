package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    val castId: Int,
    @SerializedName("name")
    val castName: String,
    @SerializedName("profile_path")
    val castProfilePath: String
)
