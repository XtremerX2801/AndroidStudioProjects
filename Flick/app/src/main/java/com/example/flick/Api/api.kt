package com.example.flick.Api

import com.example.flick.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class api {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        private fun builder(): Retrofit {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client())
                    .build()
        }

        private fun client(): OkHttpClient {
            return OkHttpClient.Builder().addNetworkInterceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()
        }

        fun createService(): TheMovieDatabaseApi {
            return builder().create(TheMovieDatabaseApi::class.java)
        }
    }
}
