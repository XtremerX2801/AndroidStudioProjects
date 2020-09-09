package com.example.stringapp.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.stringapp.Fragment.RegisterFragment
import com.example.stringapp.R

class VerifyActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verify_your_email)
        val verifyBack = findViewById<ImageView>(R.id.verify_back)

        verifyBack.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.verify_back -> backToRegisterFragment()
            }
        }
    }

    private fun backToRegisterFragment(){
        val fragment = RegisterFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.verify_your_email, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}