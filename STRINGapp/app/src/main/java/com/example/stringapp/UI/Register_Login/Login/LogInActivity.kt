package com.example.stringapp.Activity

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.stringapp.ApiService.ApiService
import com.example.stringapp.Fragment.ForgotPasswordFragment
import com.example.stringapp.Model.MobileSignIn.MobileSignIn
import com.example.stringapp.R
import com.example.stringapp.ViewModel.Repository
import com.example.stringapp.Utils.StaticVariable
import com.example.stringapp.StringApi
import com.example.stringapp.UI.MobileInterest.SelectInterestActivity
import com.example.stringapp.ViewModel.StringViewModel
import com.example.stringapp.ViewModel.StringViewModelFactory
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.log_in_account.*

class LogInActivity: AppCompatActivity(), View.OnClickListener {
    val sharedPref = StaticVariable.SHARED_PREF
    val accessToken = StaticVariable.ACCESS_TOKEN

    private var mobileSignIn: MobileSignIn = MobileSignIn()
    private lateinit var avm : StringViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_account)
        addEvent()

        login_cancel.setOnClickListener(this)
        login_forgot.setOnClickListener(this)
        login_email.setOnClickListener(this)

        avm = viewModel()
        bindViewModel()

    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.login_cancel -> goToRegisterLoginActivity()
                R.id.login_forgot -> goToForgotPasswordFragment()
                R.id.login_email -> login()
            }
        }
    }
    private fun addEvent() {
        login_email_password_edittext.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private fun goToRegisterLoginActivity(){
        val intent = Intent(this@LogInActivity, RegisterLoginActivity::class.java)
        startActivity(intent)
    }

    private fun goToForgotPasswordFragment() {
        val fragment = ForgotPasswordFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.log_in_account, fragment)
        transaction.commit()
    }

    private fun allFilled(): Boolean{
        if ((login_email_emailname_edittext == null)
            || (login_email_password_edittext == null)){
            return false
        }
        return true
    }

    private fun viewModel() : StringViewModel {
        val viewModelFactory = StringViewModelFactory(Repository(ApiService(StringApi.create())))
        return ViewModelProvider(this, viewModelFactory)[StringViewModel::class.java]
    }

    private fun login(){
        if (allFilled()){
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(ContentValues.TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }
                    val email = login_email_emailname_edittext.text.toString()
                    val password = login_email_password_edittext.text.toString()
                    val atoken = task.result?.token.toString()
                    avm.getSignInInfo(email, password, atoken)
                })
        }
    }

    private fun bindViewModel(){
        avm.loginInfo.observe(this, {
            mobileSignIn.apply { mobileSignIn = it }
            val accessToken = mobileSignIn.mobileSignInData?.accessToken
            saveAccessToken(accessToken)
            val intent = Intent(this@LogInActivity, SelectInterestActivity::class.java)
            startActivity(intent)
        })
    }

    private fun saveAccessToken(token : String?){
        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.putString(accessToken, token).apply()
        sharedPreferences.getString(accessToken, "token")?.let { Log.d("check access token", it) }
    }

    // Use these after login successfully in next activity
//    private fun loadAfterLogin(){
//        val sharedPreferences: SharedPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE)
//        // declare acc_token in onCreate
//        var acc_token: String? = sharedPreferences.getString(accessToken, "")
//    }
//
//    private fun update(){}
}