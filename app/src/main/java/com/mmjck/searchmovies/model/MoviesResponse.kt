package com.mmjck.searchmovies.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("Search")
    var search: List<Movie>,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("Response")
    var response: Boolean
)