package com.example.myapplicationss

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplicationss.Model.Headline
import com.example.myapplicationss.Model.Multimedia
import com.example.myapplicationss.Model.NewsArticle
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {
    private var article: NewsArticle = NewsArticle()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val headline = article.response!!.docs[position].headline
        val thumbnail = article.response!!.docs[position].multimedia
        if (thumbnail.isNullOrEmpty()) {
            holder.bind(headline)
        } else{
            holder.bind(headline, thumbnail[0])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if (article.response != null) {
            article.response!!.docs.size
        } else 0
    }

    inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.recycler_name
        private val image: ImageView = view.recycler_image

        fun bind(headline: Headline?, thumbnail: Multimedia?){
            name.text = headline?.main

            val imageUrl = "https://www.nytimes.com/" + thumbnail?.url
            val obj = RoundedCorners(8)
            val options = RequestOptions().transform(obj)
            Glide.with(view.context.applicationContext ?: return)
                .load(imageUrl)
                .apply(options)
                .into(image)

        }

        fun bind(headline: Headline?) {
            name.text = headline?.main

            val imageUrl = "https://vanhoadoanhnghiepvn.vn/wp-content/uploads/2020/08/112815953-stock-vector-no-image-available-icon-flat-vector.jpg"
            val obj = RoundedCorners(8)
            val options = RequestOptions().transform(obj)
            Glide.with(view.context.applicationContext ?: return)
                .load(imageUrl)
                .apply(options)
                .into(image)

        }
    }

    fun setArticle(articles: NewsArticle) {
        article.apply { article = articles }
        notifyDataSetChanged()
    }
}