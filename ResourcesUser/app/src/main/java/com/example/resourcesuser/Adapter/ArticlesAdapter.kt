package com.example.resourcesuser.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.resourcesuser.Constant
import com.example.resourcesuser.Model.Doc
import com.example.resourcesuser.Model.Headline
import com.example.resourcesuser.Model.Multimedia
import com.example.resourcesuser.Model.NewsArticle
import com.example.resourcesuser.R
import com.example.resourcesuser.Utils.onItemClickListener
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ArticlesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var article: NewsArticle = NewsArticle()
    private var documents: MutableList<Doc> = arrayListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is MyViewHolder -> {
                val headline = documents[position].headline
                val thumbnail = documents[position].multimedia
                if (thumbnail.isNullOrEmpty()) {
                    holder.bind(headline)
                } else{
                    holder.bind(headline, thumbnail[0])
                }
            }
            is LoadingViewHolder ->{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constant.VIEW_TYPE_ITEM -> MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recyclerview_item,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Different View type")
        }
    }

    override fun getItemCount(): Int {
            return documents.size
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

            view.setOnClickListener{
                itemClick?.onItemClick(documents[adapterPosition])
            }
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

            view.setOnClickListener{
                itemClick?.onItemClick(documents[adapterPosition])
            }
        }
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var itemClick: onItemClickListener?= null

    fun setItemClickListener(clickListener: onItemClickListener?) {
        itemClick = clickListener
    }

    fun setArticle(articles: NewsArticle) {
        article.apply { article = articles }

        documents.apply {
            article.response?.docs?.let { documents.addAll(it) }
        }
        notifyDataSetChanged()
    }

    fun resetAdapter(){
        documents.clear()
    }

}