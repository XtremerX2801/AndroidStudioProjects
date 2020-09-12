package com.example.stringapp.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stringapp.Adapter.InterestAdapter
import com.example.stringapp.ApiService.ApiService
import com.example.stringapp.Model.MobileInterest.Data
import com.example.stringapp.R
import com.example.stringapp.Repository.Register.Repository
import com.example.stringapp.StaticVariable
import com.example.stringapp.StringApi
import com.example.stringapp.ViewModel.StringViewModel
import com.example.stringapp.ViewModel.StringViewModelFactory
import com.example.stringapp.Utils.onItemClickListener
import kotlinx.android.synthetic.main.select_interests.*

class SelectInterestActivity: AppCompatActivity(), View.OnClickListener {
    private val sharedPref = StaticVariable.SHARED_PREF
    private val accessToken = StaticVariable.ACCESS_TOKEN

    private lateinit var interestAdapter: InterestAdapter
    private lateinit var interestViewModel: StringViewModel
    private var itemCount = 0
    private var canNext = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_interests)

        interestViewModel = viewModel()
        interestAdapter = InterestAdapter()
        getUserInterest()
        bindViewModel()

        val itemClickListener = object: onItemClickListener {
            override fun onItemInterestClick(item: Data) {
                if (interestAdapter.getItemChecked()){
                    itemCount += 1
                    interest_next.text = (3 - itemCount).toString() + " more"
                } else {itemCount -= 1
                    interest_next.text = (3 - itemCount).toString() + " more"
                }
                canNext = if (itemCount >= 3){
                    interest_next.setBackgroundResource(R.drawable.rectangle_interest)
                    interest_next.text = "Next"
                    true
                } else {
                    interest_next.setBackgroundResource(R.drawable.rectangle_interest_more)
                    false}
            }
        }
        interestAdapter.setItemClickListener(itemClickListener)
        interestAdapter.notifyDataSetChanged()
        interest_view.adapter = interestAdapter
        interest_view.layoutManager = GridLayoutManager(this, 2)

        interest_next.setOnClickListener(this)
    }

    private fun bindViewModel(){
        interestViewModel.userInterest.observe(this, {
            interestAdapter.setInterest(it)
            it.message?.let { it1 -> Log.d("check", it1) }
        })
    }

    private fun viewModel() : StringViewModel{
        val viewModelFactory = StringViewModelFactory(Repository(ApiService(StringApi.create())))
        return ViewModelProvider(this, viewModelFactory)[StringViewModel::class.java]
    }

    private fun getUserInterest(){
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE)
        interestViewModel.getInterest("Bearer " + sharedPreferences.getString(accessToken, ""))
    }

    override fun onClick(view: View?) {
        if (view != null) {
            if (view.id == R.id.interest_next){
                if (canNext) {
                    val intent = Intent(this@SelectInterestActivity, FollowPeopleActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}