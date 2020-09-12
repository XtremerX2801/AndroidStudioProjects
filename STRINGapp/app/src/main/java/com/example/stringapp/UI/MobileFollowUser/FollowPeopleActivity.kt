package com.example.stringapp.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stringapp.Adapter.UserListAdapter
import com.example.stringapp.ApiService.ApiService
import com.example.stringapp.R
import com.example.stringapp.Repository.Register.Repository
import com.example.stringapp.StaticVariable
import com.example.stringapp.StringApi
import com.example.stringapp.Utils.onItemUserListClickListener
import com.example.stringapp.ViewModel.StringViewModel
import com.example.stringapp.ViewModel.StringViewModelFactory
import kotlinx.android.synthetic.main.select_follow_people.*

class FollowPeopleActivity: AppCompatActivity(), View.OnClickListener {
    private val sharedPref = StaticVariable.SHARED_PREF
    private val accessToken = StaticVariable.ACCESS_TOKEN

    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userListViewModel: StringViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_follow_people)

        userListViewModel = viewModel()
        userListAdapter = UserListAdapter()
        getUserList()
        bindViewModel()

        val itemClickListener = object: onItemUserListClickListener {
            override fun onItemUserListClick(follow: TextView) {
                val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE)
                userListAdapter.getUserId()?.let { userListViewModel.getUserFollowed(it, "Bearer " + sharedPreferences.getString(accessToken, "")) }
                Log.d("UserID",userListAdapter.getUserId().toString())
        }}
        userListAdapter.setItemClickListener(itemClickListener)
        userListAdapter.notifyDataSetChanged()
        follow_people_view.adapter = userListAdapter
        follow_people_view.layoutManager = GridLayoutManager(this, 1)

        follow_people_done.setOnClickListener(this)
        follow_people_back.setOnClickListener(this)
    }

    private fun bindViewModel(){
        userListViewModel.userList.observe(this, {
            userListAdapter.setUserList(it)
        })
    }

    private fun viewModel() : StringViewModel{
        val viewModelFactory = StringViewModelFactory(Repository(ApiService(StringApi.create())))
        return ViewModelProvider(this, viewModelFactory)[StringViewModel::class.java]
    }

    private fun getUserList(){
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE)
        Log.d("check in interest", "Bearer " + sharedPreferences.getString(accessToken, ""))
        userListViewModel.getUserList("Bearer " + sharedPreferences.getString(accessToken, ""))
    }

    private fun goBackSelectInterest(){
        val intent = Intent(this@FollowPeopleActivity, SelectInterestActivity::class.java)
        startActivity(intent)
    }

    private fun done(){
        val intent = Intent(this@FollowPeopleActivity, MobileFeedActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            if (view.id == R.id.follow_people_done){
                done()
            }
            if (view.id == R.id.follow_people_back){
                goBackSelectInterest()
            }
        }
    }
}