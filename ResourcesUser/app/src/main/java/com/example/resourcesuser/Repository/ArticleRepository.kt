package com.example.resourcesuser.Repository

import androidx.lifecycle.MutableLiveData
import com.example.resourcesuser.Api.NewsApiService
import com.example.resourcesuser.Model.NewsArticle
import com.example.resourcesuser.Model.Result

class ArticleRepository (private val apiService: NewsApiService) {

    fun getSearch(page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchDefault(
            page = page,
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

    fun getSearchQuery(searchQuery: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQuery(
            page = page,
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

    fun getSearchQueryBeginDate(searchQuery: String, beginDate: Int, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDate(
            page = page,
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

    fun getSearchQuerySort(searchQuery: String, sorting: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQuerySort(
            page = page,
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

    fun getSearchQueryBeginDateSort(searchQuery: String, beginDate: Int, sorting: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDateSort(
            page = page,
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

    fun getSearchQueryNewsDesk(searchQuery: String, news_desk: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryNewsDesk(
            page = page,
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

    fun getSearchQueryBeginDateNewsDesk(searchQuery: String, beginDate: Int, news_desk: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDateNewsDesk(
            page = page,
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

    fun getSearchQuerySortNewsDesk(searchQuery: String, news_desk: String, sorting: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQuerySortNewsDesk(
            page = page,
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

    fun getSearchQueryBeginDateSortNewsDesk(searchQuery: String, beginDate: Int, sorting: String, news_desk: String, page: Int): Result<NewsArticle> {
        val responseArticle = MutableLiveData<NewsArticle>()
        apiService.getSearchQueryBeginDateSortNewsDesk(
            page = page,
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