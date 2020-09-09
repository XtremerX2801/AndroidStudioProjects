package com.example.stringapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stringapp.Activity.LogInActivity
import com.example.stringapp.Activity.MainActivity
import com.example.stringapp.Activity.RegisterLoginActivity
import com.example.stringapp.Activity.VerifyActivity
import com.example.stringapp.ApiService.ApiService
import com.example.stringapp.Model.MobileSignIn.MobileSignIn
import com.example.stringapp.Model.Register.MobileRegister
import com.example.stringapp.R
import com.example.stringapp.Repository.Register.Repository
import com.example.stringapp.StringApi
import com.example.stringapp.ViewModel.StringViewModel
import com.example.stringapp.ViewModel.StringViewModelFactory
import kotlinx.android.synthetic.main.fragment_register_email.*

class RegisterFragment : Fragment(), View.OnClickListener{

    private lateinit var avm : StringViewModel
    private var mobileRegister: MobileRegister = MobileRegister()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register_email, container, false)

        val registerBack = view.findViewById<ImageView>(R.id.register_back)
        val registerCheckbox = view.findViewById<CheckBox>(R.id.register_account_checkbox)
        val registerSignUp = view.findViewById<TextView>(R.id.register_sign_up)
        val registerLogIn = view.findViewById<TextView>(R.id.register_log_in)

        registerBack.setOnClickListener(this)
        registerCheckbox.setOnClickListener(this)
        registerSignUp.setOnClickListener(this)
        registerLogIn.setOnClickListener(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { view, motionEvent -> return@setOnTouchListener true }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when(view.id){
                R.id.register_back -> backToRegisterLoginActivity()
                R.id.register_account_checkbox -> changeSignUpBackground()
                R.id.register_sign_up -> goToLogInForSignUp()
                R.id.register_log_in -> goToLogIn()
            }
        }
    }

    private fun backToRegisterLoginActivity(){
        val intent = Intent(activity, RegisterLoginActivity::class.java)
        startActivity(intent)
    }

    private fun changeSignUpBackground(){
        if (register_account_checkbox.isChecked){
            register_sign_up.setBackgroundResource(R.drawable.rectangle_sign_in_1)
        } else {
            register_sign_up.setBackgroundResource(R.drawable.rectangle_sign_in_0)
        }
    }

    private fun goToLogInForSignUp(){
        register()
        bindViewModel()
        if ((register_account_checkbox.isChecked) && ( allFilled() )){
            val intent = Intent(activity, VerifyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToLogIn(){
        val intent = Intent(activity, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun viewModel() : StringViewModel {
        val viewModelFactory = StringViewModelFactory(Repository(ApiService(StringApi.create())))
        return ViewModelProvider(this, viewModelFactory)[StringViewModel::class.java]
    }

    private fun bindViewModel(){
        avm.registerInfo.observe(this, {
            mobileRegister.apply{mobileRegister = it}
            mobileRegister.message?.let { it1 -> Log.d("check in register", it1) }
        })
    }

    private fun allFilled(): Boolean{
        if ((register_email_emailname_edittext.text == null)
            || (register_email_name_edittext.text == null)
            || (register_email_dateOfBirth_edittext.text == null)
            || (register_email_username_edittext.text == null)
            || (register_email_password_edittext.text == null)
            || (register_email_confirmPassword_edittext.text == null)){
            return false
        }
        return true
    }

    private fun register(){
        if (allFilled() && (register_account_checkbox.isChecked) && (register_email_password_edittext.text.toString() == register_email_confirmPassword_edittext.text.toString())){
            avm = viewModel()
            avm.getRegisterInfo(register_email_username_edittext.text.toString()
                , register_email_name_edittext.text.toString()
                , register_email_dateOfBirth_edittext.text.toString()
                , register_email_emailname_edittext.text.toString()
                , register_email_password_edittext.text.toString()
                , register_email_confirmPassword_edittext.text.toString())
        }
    }
}