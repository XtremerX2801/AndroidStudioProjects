package com.example.resourcesuser.Api

import com.example.resourcesuser.Model.NewsArticle

class NewsApiService (private val apiApi: NewsApi) {

    fun getSearchDefault(
        page : Int,
        onSuccess : (NewsArticle?) -> Unit,
        onError : (String) -> Unit
    ){
        val request = apiApi.getSearchDefault(page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQuery(
        page : Int,
        query: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQuery(query, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDate(
        page : Int,
        query: String,
        date: Int,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDate(query, date, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQuerySort(
        page : Int,
        query: String,
        sort: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQuerySort(query, sort, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDateSort(
        page : Int,
        query: String,
        date: Int,
        sort: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDateSort(query, date, sort, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryNewsDesk(
        page : Int,
        query: String,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryNewsDesk(query, news_desk, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDateNewsDesk(
        page : Int,
        query: String,
        date: Int,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDateNewsDesk(query, date, news_desk, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQuerySortNewsDesk(
        page : Int,
        query: String,
        sort: String,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQuerySortNewsDesk(query, sort, news_desk, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    fun getSearchQueryBeginDateSortNewsDesk(
        page : Int,
        query: String,
        date: Int,
        sort: String,
        news_desk: String,
        onSuccess: (NewsArticle?) -> Unit,
        onError: (String) -> Unit
    ) {
        val request = apiApi.getQueryBeginDateSortNewsDesk(query, date, sort, news_desk, page)
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

}