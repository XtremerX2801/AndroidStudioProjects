package com.example.resourcesuser.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.resourcesuser.Model.NewsArticle
import com.example.resourcesuser.Model.Result
import com.example.resourcesuser.Repository.ArticleRepository

class ArticleViewModel(private val articleRepository: ArticleRepository): ViewModel() {

    private val requestDefault = MutableLiveData<Result<NewsArticle>>()
    private var searchQuery = MutableLiveData<String>()
    private var beginDate = MutableLiveData<Int>()
    private var sort = MutableLiveData<String>()
    private var news_desk = MutableLiveData<String>()
    private var page = MutableLiveData<Int>()

    val news = Transformations.switchMap(requestDefault){
        it.data
    }

    fun getSearch(pageNumber: Int){
        page.value = pageNumber
        requestDefault.value = articleRepository.getSearch(pageNumber)
    }

    fun getSearchQuery(query: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        requestDefault.value = articleRepository.getSearchQuery(query, pageNumber)
    }

    fun getSearchQueryBeginDate(query: String, date: Int, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        beginDate.value = date
        requestDefault.value = articleRepository.getSearchQueryBeginDate(query, date, pageNumber)
    }

    fun getSearchQuerySort(query: String, sorting: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        sort.value = sorting
        requestDefault.value = articleRepository.getSearchQuerySort(query, sorting, pageNumber)
    }

    fun getSearchQueryBeginDateSort(query: String, date: Int, sorting: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        beginDate.value = date
        sort.value = sorting
        requestDefault.value = articleRepository.getSearchQueryBeginDateSort(query, date, sorting, pageNumber)
    }

    fun getSearchQueryNewsDesk(query: String, newsDesk: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQueryNewsDesk(query, newsDesk, pageNumber)
    }

    fun getSearchQueryBeginDateNewsDesk(query: String, date: Int, newsDesk: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        beginDate.value = date
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQueryBeginDateNewsDesk(query, date, newsDesk, pageNumber)
    }

    fun getSearchQuerySortNewsDesk(query: String, sorting: String, newsDesk: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        sort.value = sorting
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQuerySortNewsDesk(query, sorting, newsDesk, pageNumber)
    }

    fun getSearchQueryBeginDateSortNewsDesk(query: String, date: Int, sorting: String, newsDesk: String, pageNumber: Int){
        page.value = pageNumber
        searchQuery.value = query
        beginDate.value = date
        sort.value = sorting
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQueryBeginDateSortNewsDesk(query, date, sorting, newsDesk, pageNumber)
    }

    fun getQuery(): String?{
        return searchQuery.value
    }

    fun getBeginDate(): Int?{
        return beginDate.value
    }

    fun getSort(): String?{
        return sort.value
    }

    fun getNewsDesk(): String?{
        return news_desk.value
    }

    fun getPage(): Int?{
        return page.value
    }
}