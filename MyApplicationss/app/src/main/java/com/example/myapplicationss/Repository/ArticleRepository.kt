package com.example.myapplicationss.Repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplicationss.Api.NewsApiService
import com.example.myapplicationss.Model.NewsArticle
import com.example.myapplicationss.Model.Result

class ArticleRepository (private val apiService: NewsApiService) {

    fun getSearch(): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchDefault(
            onSuccess = {response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQuery(searchQuery: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQuery(
            query = searchQuery,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQueryBeginDate(searchQuery: String, beginDate: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDate(
            query = searchQuery,
            date = beginDate,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQuerySort(searchQuery: String, sorting: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQuerySort(
            query = searchQuery,
            sort = sorting,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQueryBeginDateSort(searchQuery: String, beginDate: Int, sorting: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDateSort(
            query = searchQuery,
            date = beginDate,
            sort = sorting,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQueryNewsDesk(searchQuery: String, news_desk: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryNewsDesk(
            query = searchQuery,
            news_desk = news_desk,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQueryBeginDateNewsDesk(searchQuery: String, beginDate: Int, news_desk: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDateNewsDesk(
            query = searchQuery,
            date = beginDate,
            news_desk = news_desk,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQuerySortNewsDesk(searchQuery: String, news_desk: String, sorting: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQuerySortNewsDesk(
            query = searchQuery,
            sort = sorting,
            news_desk = news_desk,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }

    fun getSearchQueryBeginDateSortNewsDesk(searchQuery: String, beginDate: Int, sorting: String, news_desk: String): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDateSortNewsDesk(
            query = searchQuery,
            date = beginDate,
            sort = sorting,
            news_desk = news_desk,
            onSuccess = { response ->
                responseArticle.value = response ?: NewsArticle()
            },
            onError = {
            }
        )
        return Result(
            data = responseArticle
        )
    }
}