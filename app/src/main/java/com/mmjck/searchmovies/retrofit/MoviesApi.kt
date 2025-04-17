package com.mmjck.searchmovies.retrofit

import com.mmjck.searchmovies.model.Movie
import com.mmjck.searchmovies.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(".")
    fun getInfoAdditional(
        @Query("i") imdbId: String,
        @Query("apikey") apikey: String = "b3e9d173"
    ): Call<Movie>

    @GET(".")
    fun getMovies(
        @Query("s") title: String,
        @Query("apikey") apikey: String = "b3e9d173"
    ): Call<MoviesResponse>
}