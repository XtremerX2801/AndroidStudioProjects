package com.example.stringapp.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stringapp.Fragment.ViewPagerManager
import com.example.stringapp.R
import com.example.stringapp.Utils.StaticVariable

class SplashActivity: AppCompatActivity() {
    private val sharedPref = StaticVariable.SHARED_PREF
    private val accessToken = StaticVariable.ACCESS_TOKEN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        startProgress()
    }

    private fun startProgress() {
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE)
                    sharedPreferences.getString(accessToken, "")?.let { Log.d("token", it) }
                    if (sharedPreferences.getString(accessToken, "") != null){
                        val mainIntent = Intent(this@SplashActivity, LogInActivity::class.java)
                        startActivity(mainIntent)
                    } else {
                        val mainIntent = Intent(this@SplashActivity, ViewPagerManager::class.java)
                        startActivity(mainIntent)
                    }
                }
            }
        }
        thread.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}