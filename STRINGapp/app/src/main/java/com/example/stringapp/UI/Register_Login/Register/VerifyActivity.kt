package com.example.stringapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.stringapp.Fragment.RegisterFragment
import com.example.stringapp.R
import kotlinx.android.synthetic.main.verify_your_email.*

class VerifyActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verify_your_email)

        verify_back.setOnClickListener(this)
        verify_email.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.verify_back -> backToRegisterFragment()
            R.id.verify_email -> goToLogIn()
        }
    }

    private fun goToLogIn(){
        val intent = Intent(this@VerifyActivity, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun backToRegisterFragment(){
        val fragment = RegisterFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.verify_your_email, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}