package com.example.myapplicationss.Model

import androidx.lifecycle.LiveData

data class Result<T>(
    val data : LiveData<T>
)