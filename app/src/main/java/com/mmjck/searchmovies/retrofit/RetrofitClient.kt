package com.mmjck.searchmovies.retrofit;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    companion object {
        const val BASE_URL = "https://www.omdbapi.com/"

        private fun getInstance(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }

        val moviesApi: MoviesApi by lazy {
            RetrofitClient.getInstance()
                .create(MoviesApi::class.java)
        }
    }
}
