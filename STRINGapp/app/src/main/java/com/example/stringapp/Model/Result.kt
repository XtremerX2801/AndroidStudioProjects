package com.example.stringapp.Model

import androidx.lifecycle.LiveData

data class Result<T>(
    val data : LiveData<T>?
)