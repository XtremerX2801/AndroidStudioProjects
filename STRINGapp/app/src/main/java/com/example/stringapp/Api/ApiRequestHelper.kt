package com.example.stringapp.Api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiRequestHelper {
    inline fun <T> asyncRequest(request: Call<T>,
                                crossinline onSuccess: (T?) -> Unit,
                                crossinline onError: (String) -> Unit) {

        request.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onError("error code: ${response.code()}")
                }
            }
        })
    }
}