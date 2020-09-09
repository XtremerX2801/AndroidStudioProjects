package com.example.stringapp

import com.example.stringapp.Model.FollowUser.FollowUser
import com.example.stringapp.Model.MobileFollow.MobileFollow
import com.example.stringapp.Model.MobileInterest.MobileInterest
import com.example.stringapp.Model.Register.MobileRegister
import com.example.stringapp.Model.MobileSignIn.MobileSignIn
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface StringApi {
    companion object {
        private const val BASE_URL = "http://string-api.vinova.sg/api/"

        fun create(): StringApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StringApi::class.java)
        }
    }

    @FormUrlEncoded
    @POST("users-register-email")
    fun userRegisterEmail(
        @Field("username") Username: String,
        @Field("name") Name: String,
        @Field("date_of_birth") DoB: String,
        @Field("email") Email: String,
        @Field("password") Pass: String,
        @Field("confirm_password") Con_Pass: String
    ): Call<MobileRegister>

    @FormUrlEncoded
    @POST("users-login")
    fun userSignInEmail(
        @Field("username") Email: String,
        @Field("password") Pass: String,
        @Field("fcm_token") fcm_token: String
    ): Call<MobileSignIn>

    @GET("interest-categories-list")
    fun userInterest(
        @Header("Authorization") authorization: String
    ): Call<MobileInterest>

    @GET("users-list")
    fun userList(
        @Header("Authorization") authorization: String
    ): Call<MobileFollow>

    @POST("follow-users")
    fun followUser(
        @Query("users_id") userId: Int,
        @Header("Authorization") authorization: String
    ): Call<FollowUser>
}