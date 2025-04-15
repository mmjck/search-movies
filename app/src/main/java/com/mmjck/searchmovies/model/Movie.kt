package com.mmjck.searchmovies.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("Title")
    var title: String,
    @SerializedName("Released")
    var year: String,
    @SerializedName("Poster")
    var poster: String,
    @SerializedName("Runtime")
    var runtime: String,
    @SerializedName("imdbRating")
    var rating: Float,
    @SerializedName("Plot")
    var plot: String,
    @SerializedName("imdbID")
    var id: String,
    @SerializedName("Director")
    var director: String
)