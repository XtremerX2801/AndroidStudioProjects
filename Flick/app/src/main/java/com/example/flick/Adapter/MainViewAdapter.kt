package com.example.flick.Adapter

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.flick.Model.Result
import com.example.flick.R
import com.bumptech.glide.request.RequestOptions
import com.example.flick.Util.onItemClickListener

class MainViewAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return High_ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.high_main_movie_view,
                            parent,
                            false
                    )
            )
        }
        else{
            return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.main_movie_view,
                            parent,
                            false
                    )
            )
        }
    }

    private var movie: MutableList<Result> = ArrayList()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is ViewHolder -> {
                holder.bind(movie[position])
            }
            is High_ViewHolder ->{
                holder.bind(movie[position])
            }
        }
    }

    inner class ViewHolder(val normalItemView: View): RecyclerView.ViewHolder(normalItemView) {
        val movieImage: ImageView = normalItemView.findViewById(R.id.image_normal)
        val movieTitle: TextView = normalItemView.findViewById(R.id.title_normal)
        val movieOverview: TextView = normalItemView.findViewById(R.id.overview_normal)
        val normalMovieRating = normalItemView.findViewById<TextView>(R.id.normal_rating)

        fun bind(movieProperties: Result?) {

            movieTitle.text = movieProperties?.title
            movieOverview.text = movieProperties?.overview
            normalMovieRating.text = movieProperties?.voteAverage.toString()

            val orientation = normalItemView.context.resources.configuration.orientation
            var linkImage = "https://image.tmdb.org/t/p/"
            if (orientation == 1) {
                linkImage += ("w200" + movieProperties?.posterPath)
            } else {
                linkImage += ("w500" + movieProperties?.backdropPath)
            }
            val obj = RoundedCorners(8)
            val options = RequestOptions().transform(obj)
                    .error(R.drawable.no_image)
            Glide.with(normalItemView.context.applicationContext ?: return)
                    .load(linkImage)
                    .fitCenter()
                    .apply(options)
                    .into(movieImage)

            normalItemView.setOnClickListener{
                if (movieProperties != null) {
                    itemClick?.onItemClick(movieProperties)
                }
            }
        }
    }

    inner class High_ViewHolder(val highItemView: View): RecyclerView.ViewHolder(highItemView){
        val highMovieImage = highItemView.findViewById<ImageView>(R.id.image_high_rate)
        val highMovieTitle = highItemView.findViewById<TextView>(R.id.title_high_rate)
        val highMovieRating = highItemView.findViewById<TextView>(R.id.high_rating)

        fun bind(movieProperties: Result?) {

            highMovieTitle.text = movieProperties?.title
            highMovieRating.text = movieProperties?.voteAverage.toString()

            var linkImage = "https://image.tmdb.org/t/p/"
            linkImage += ("w500" + movieProperties?.backdropPath)
            val obj= RoundedCorners(8)
            val options = RequestOptions().transform(obj)
                    .error(R.drawable.no_image)
            Glide.with(highItemView.context.applicationContext ?: return)
                    .load(linkImage)
                    .fitCenter()
                    .apply(options)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(highMovieImage)

            highItemView.setOnClickListener{
                if (movieProperties != null) {
                    itemClick?.onItemClick(movieProperties)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    var itemClick: onItemClickListener?= null

    fun setItemClickListener(clickListener: onItemClickListener?) {
        itemClick = clickListener;
    }

    fun addAll(mFilms:List<Result>)
    {
        val lastSize=movie.size
        movie.addAll(mFilms)
        notifyItemRangeInserted(lastSize,mFilms.size)
    }
    fun updateSource(mFilms:List<Result>)
    {
        movie.clear()
        movie.addAll(mFilms)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val average = movie[position].voteAverage
        return if (average != null) {
            if (average > 7) 1 else 2
        } else 0
    }
}
