package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class Produce(
    @SerializedName("id")
    val produceID: Int,
    @SerializedName("logo_path")
    val produceLogo: String,
    @SerializedName("name")
    val produceName: String,
    @SerializedName("origin_country")
    val produceCountry: String
)
