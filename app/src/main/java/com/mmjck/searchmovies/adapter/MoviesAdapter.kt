package com.mmjck.searchmovies.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mmjck.searchmovies.R
import com.mmjck.searchmovies.model.Movie
import com.mmjck.searchmovies.retrofit.RetrofitClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesAdapter(private val list: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var context: Context


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        var title: TextView? = null
        var year: TextView? = null
        var poster: ImageView? = null
        var rank: TextView? = null
        var durantion: TextView? = null
        var plot: TextView? = null


        init {
            title = view.findViewById(R.id.title_movie)
            year = view.findViewById(R.id.year_movie)
            poster = view.findViewById(R.id.poster_movie)
            rank = view.findViewById(R.id.to_rank)
            durantion = view.findViewById(R.id.duration)
            plot = view.findViewById(R.id.plot)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = list[position].title
        Picasso.get().load(list[position].poster).into(holder.poster)

        setInfoAdditional(holder, list[position].id)
    }

    private fun setInfoAdditional(holder: ViewHolder, id: String) {
        val callback = RetrofitClient.moviesApi.getInfoAdditional(id)

        callback.enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val movie = response.body()

                holder.rank?.text = movie?.rating.toString()
                holder.durantion?.text = movie?.runtime.toString()
                holder.plot?.text = movie?.plot.toString()
                holder.year?.text = movie?.year.toString()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.message?.let {
                    Log.d("ERROR", it)
                }
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show ()            }

        })
    }

}