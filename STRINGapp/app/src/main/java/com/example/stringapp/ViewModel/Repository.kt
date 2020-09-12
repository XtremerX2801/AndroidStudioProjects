package com.example.stringapp.Repository.Register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.stringapp.ApiService.ApiService
import com.example.stringapp.Model.FollowUser.FollowUser
import com.example.stringapp.Model.MobileFeed.MobileFeed
import com.example.stringapp.Model.MobileFollow.MobileFollow
import com.example.stringapp.Model.MobileInterest.MobileInterest
import com.example.stringapp.Model.MobileSignIn.MobileSignIn
import com.example.stringapp.Model.Register.MobileRegister
import com.example.stringapp.Model.Result

class Repository (private val apiService: ApiService) {

    fun getRegisterInfo(username: String, name: String, DoB: String, email: String, pass: String, con_pass: String): Result<MobileRegister> {
        val responseRegister = MutableLiveData<MobileRegister>()
        apiService.getRegisterInfo(
            Username = username,
            Name = name,
            DoB = DoB,
            Email = email,
            Pass = pass,
            Con_Pass = con_pass,
            onSuccess = {response ->
                Log.d("check in repository", response.toString())
                responseRegister.value = response ?: MobileRegister()
            },
            onError = {response ->
                Log.d("Error", response)
            }
        )
        return Result(
            data = responseRegister
        )
    }

    fun getSignInInfo(email: String, pass: String, fcm: String): Result<MobileSignIn> {
        val responseLogin = MutableLiveData<MobileSignIn>()
        apiService.getSignInInfo(
            Email = email,
            Pass = pass,
            fcm_token = fcm,
            onSuccess = {response ->
                Log.d("check in repository", response.toString())
                responseLogin.value = response ?: MobileSignIn()
            },
            onError = {response ->
                Log.d("Error", response)
            }
        )
        return Result(
            data = responseLogin
        )
    }

    fun getUserInterest(authorization: String): Result<MobileInterest> {
        val responseInterest = MutableLiveData<MobileInterest>()
        apiService.getInterest(
            Authorization = authorization,
            onSuccess = {response ->
                Log.d("check in repository", response.toString())
                responseInterest.value = response ?: MobileInterest()
            },
            onError = {response ->
                Log.d("Error", response)
            }
        )
        return Result(
            data = responseInterest
        )
    }

    fun getUserList(authorization: String): Result<MobileFollow> {
        val responseUserList = MutableLiveData<MobileFollow>()
        apiService.getUserList(
            Authorization = authorization,
            onSuccess = {response ->
                Log.d("check in repository", response.toString())
                responseUserList.value = response ?: MobileFollow()
            },
            onError = {response ->
                Log.d("Error", response)
            }
        )
        return Result(
            data = responseUserList
        )
    }

    fun getUserFollowed(userId: Int, authorization: String): Result<FollowUser> {
        val responseUserFollowed = MutableLiveData<FollowUser>()
        apiService.sendUserFollowed(
            user_id = userId,
            Authorization = authorization,
            onSuccess = {response ->
                Log.d("check in repository", response.toString())
                responseUserFollowed.value = response ?: FollowUser()
            },
            onError = {response ->
                Log.d("Error", response)
            }
        )
        return Result(
            data = responseUserFollowed
        )
    }

    fun getMobileFeed(page: Int, current_per_page: Int, authorization: String): Result<MobileFeed> {
        val responseMobileFeed = MutableLiveData<MobileFeed>()
        apiService.getMobileFeed(
            page = page,
            current_per_page = current_per_page,
            Authorization = authorization,
            onSuccess = {response ->
                Log.d("check in repository", response.toString())
                responseMobileFeed.value = response ?: MobileFeed()
            },
            onError = {response ->
                Log.d("Error", response)
            }
        )
        return Result(
            data = responseMobileFeed
        )
    }
}