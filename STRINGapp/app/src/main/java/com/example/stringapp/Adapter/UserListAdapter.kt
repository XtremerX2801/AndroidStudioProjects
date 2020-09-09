package com.example.stringapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.stringapp.Model.MobileFollow.Data
import com.example.stringapp.Model.MobileFollow.MobileFollow
import com.example.stringapp.R
import com.example.stringapp.Utils.onItemUserListClickListener
import kotlinx.android.synthetic.main.follow_people_item.view.*

class UserListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var userList: MobileFollow = MobileFollow()
    private var data: MutableList<Data> = arrayListOf()
    private var isFollowed = false
    private var dataPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyUserListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.follow_people_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MyUserListViewHolder -> {
                val myData = data[position]
                holder.bind(myData)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    var itemClick: onItemUserListClickListener?= null

    fun setItemClickListener(clickListener: onItemUserListClickListener?) {
        itemClick = clickListener
    }

    fun itemCheck(text: TextView){
        isFollowed = text.text.toString() == "Unfollow"
    }

    fun getUserId(): Int?{
        return data[dataPosition].id
    }

    inner class MyUserListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val followPeopleFollow: TextView = view.follow_people_item_follow
        private val followPeopleName: TextView = view.item_follow_people_name
        private val image1: ImageView = view.follow_people_image1
        private val image2: ImageView = view.follow_people_image2
        private val image3: ImageView = view.follow_people_image3

        fun bind(userListData: Data){
            followPeopleName.text = userListData.username

            val imageUrl1 = userListData.photos?.get(0)?.url?.medium
            val obj1 = RoundedCorners(8)
            val options1 = RequestOptions().transform(obj1)
            Glide.with(view.context.applicationContext ?: return)
                .load(imageUrl1)
                .apply(options1)
                .into(image1)

            val imageUrl2 = userListData.photos?.get(0)?.url?.original
            val obj2 = RoundedCorners(8)
            val options2 = RequestOptions().transform(obj2)
            Glide.with(view.context.applicationContext ?: return)
                .load(imageUrl2)
                .apply(options2)
                .into(image2)

            val imageUrl3 = userListData.photos?.get(0)?.url?.thumb
            val obj3 = RoundedCorners(8)
            val options3 = RequestOptions().transform(obj3)
            Glide.with(view.context.applicationContext ?: return)
                .load(imageUrl3)
                .apply(options3)
                .into(image3)

            view.setOnClickListener{
                if (followPeopleFollow.text == "Follow"){
                    followPeopleFollow.text = "Unfollow"
                } else followPeopleFollow.text = "Follow"
                dataPosition = adapterPosition
                itemCheck(followPeopleFollow)
                itemClick?.onItemUserListClick(followPeopleFollow)
            }
        }
    }

    fun setUserList(userListData: MobileFollow) {
        userList.apply { userList = userListData }

        data.apply {
            userListData.mobileFollowData?.let { data.addAll(it) }
        }
        notifyDataSetChanged()
    }
}