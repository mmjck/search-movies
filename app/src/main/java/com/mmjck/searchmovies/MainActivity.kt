package com.mmjck.searchmovies

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mmjck.searchmovies.model.Movie
import com.mmjck.searchmovies.model.MoviesResponse

import com.mmjck.searchmovies.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var btnSearch: ImageButton
    private lateinit var editTextSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        btnSearch = findViewById(R.id.btn_search)
        editTextSearch = findViewById(R.id.search_movie)


        bindings()
    }


    private fun bindings(){
        btnSearch.setOnClickListener {
            getData(editTextSearch.text.toString())
            editTextSearch.hideKeyboard()
        }
    }

    fun getData(text: String) {
        val callback = RetrofitClient.moviesApi.getMovies(text)

        callback.enqueue (object : Callback<MoviesResponse>  {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {

                val l = response.body()?.search

                if(l != null){
                    addData(l)
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.message?.let {
                    Log.d("ERROR", it)
                }

                Toast.makeText (baseContext, "Oops, movie not found!",Toast.LENGTH_SHORT).show ()            }
        })
    }

    fun  View.hideKeyboard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


    fun addData(list: List<Movie>) {
        var recyclerView = findViewById<RecyclerView>(R.id.list_movies)    }

}
