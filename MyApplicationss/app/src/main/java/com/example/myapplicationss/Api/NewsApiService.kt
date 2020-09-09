package com.example.myapplicationss.Api

import com.example.myapplicationss.Model.NewsArticle

class NewsApiService (private val apiApi: NewsApi) {

    fun getSearchDefault(
        onSuccess : (NewsArticle?) -> Unit,
        onError : (String) -> Unit
    ){
        val request = apiApi.getSearchDefault()
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQuery(
        query: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQuery(query)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDate(
        query: String,
        date: Int,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDate(query, date)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQuerySort(
        query: String,
        sort: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQuerySort(query, sort)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDateSort(
        query: String,
        date: Int,
        sort: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDateSort(query, date, sort)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryNewsDesk(
        query: String,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryNewsDesk(query, news_desk)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDateNewsDesk(
        query: String,
        date: Int,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDateNewsDesk(query, date, news_desk)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQuerySortNewsDesk(
        query: String,
        sort: String,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQuerySortNewsDesk(query, sort, news_desk)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDateSortNewsDesk(
        query: String,
        date: Int,
        sort: String,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDateSortNewsDesk(query, date, sort, news_desk)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

}