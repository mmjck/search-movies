package com.mmjck.searchmovies.retrofit;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    companion object {
        fun getInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        }

        val moviesApi: MoviesApi by lazy {
            RetrofitClient.getInstance("http://www.omdbapi.com/")
                .create(MoviesApi::class.java)
        }
    }
}
