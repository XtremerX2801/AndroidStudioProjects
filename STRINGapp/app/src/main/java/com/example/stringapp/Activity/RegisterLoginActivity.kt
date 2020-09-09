package com.example.stringapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.stringapp.Fragment.RegisterFragment
import com.example.stringapp.R
import kotlinx.android.synthetic.main.register_login_activity.*

class RegisterLoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_login_activity)

        register_email.setOnClickListener(this)
        log_in.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id){
                R.id.register_email -> goToRegisterFragment()
                R.id.log_in -> goToLogInFragment()
            }
        }
    }

    private fun goToRegisterFragment(){
        val fragment = RegisterFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.register_login_activity, fragment)
        transaction.commit()
    }

    private fun goToLogInFragment(){
        val intent = Intent(this@RegisterLoginActivity, LogInActivity::class.java)
        startActivity(intent)
    }

}