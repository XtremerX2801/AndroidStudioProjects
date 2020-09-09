package com.example.resourcesuser.Model

import androidx.lifecycle.LiveData

data class Result<T>(
    val data : LiveData<T>
)