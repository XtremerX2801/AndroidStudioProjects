package com.example.stringapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.stringapp.Activity.LogInActivity
import com.example.stringapp.R

class ForgotPasswordFragment: Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.forgot_password, container, false)


        val forgotPasswordBack = view.findViewById<ImageView>(R.id.forgot_password_back)

        forgotPasswordBack.setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { _, _ -> return@setOnTouchListener true }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.forgot_password_back -> goToLogInActivity()
            }
        }
    }

    private fun goToLogInActivity(){
        val intent = Intent(activity, LogInActivity::class.java)
        startActivity(intent)
    }
}