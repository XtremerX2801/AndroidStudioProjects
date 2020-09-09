package com.example.myapplicationss.Api

import com.example.myapplicationss.Model.NewsArticle
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/search/v2/"

        private fun client(): OkHttpClient {
            return OkHttpClient.Builder().addNetworkInterceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("api-key", "ih1e8zrQwgirWt70cbX03UPtPQgmAiA2")
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()
        }

        fun create(): NewsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }
    }

    @GET("articlesearch.json")
    fun getSearchDefault(): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQuery(@Query("q") query: String): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQueryBeginDate(@Query("q") query: String, @Query("begin_date") start_date: Int): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQuerySort(@Query("q") query: String, @Query("sort") sort: String): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQueryBeginDateSort(@Query("q") query: String, @Query("begin_date") start_date: Int, @Query("sort") sort: String): Call<NewsArticle>

//    fq=news_desk:("Education"%20"Health")
    @GET("articlesearch.json?")
    fun getQueryNewsDesk(@Query("q") query: String, @Query("fq") news_desk: String): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQueryBeginDateNewsDesk(@Query("q") query: String, @Query("begin_date") start_date: Int, @Query("fq") news_desk: String): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQuerySortNewsDesk(@Query("q") query: String, @Query("sort") sort: String, @Query("fq") news_desk: String): Call<NewsArticle>

    @GET("articlesearch.json?")
    fun getQueryBeginDateSortNewsDesk(@Query("q") query: String, @Query("begin_date") start_date: Int, @Query("sort") sort: String, @Query("fq") news_desk: String): Call<NewsArticle>

}