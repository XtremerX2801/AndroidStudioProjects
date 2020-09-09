package com.example.stringapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stringapp.Repository.Register.Repository

class StringViewModelFactory (private val stringRepository: Repository)
    : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) = StringViewModel(stringRepository) as T
}