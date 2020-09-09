package com.example.myapplicationss.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplicationss.Model.NewsArticle
import com.example.myapplicationss.Repository.ArticleRepository
import com.example.myapplicationss.Model.Result

class ArticleViewModel(private val articleRepository: ArticleRepository): ViewModel() {

    private val requestDefault = MutableLiveData<Result<NewsArticle>>()
    private var searchQuery = MutableLiveData<String>()
    private var beginDate = MutableLiveData<Int>()
    private var sort = MutableLiveData<String>()
    private var news_desk = MutableLiveData<String>()

    val news = Transformations.switchMap(requestDefault){
        it.data
    }

    fun getSearch(){
        requestDefault.value = articleRepository.getSearch()
    }

    fun getSearchQuery(query: String){
        searchQuery.value = query
        requestDefault.value = articleRepository.getSearchQuery(query)
    }

    fun getSearchQueryBeginDate(query: String, date: Int){
        searchQuery.value = query
        beginDate.value = date
        requestDefault.value = articleRepository.getSearchQueryBeginDate(query, date)
    }

    fun getSearchQuerySort(query: String, sorting: String){
        searchQuery.value = query
        sort.value = sorting
        requestDefault.value = articleRepository.getSearchQuerySort(query, sorting)
    }

    fun getSearchQueryBeginDateSort(query: String, date: Int, sorting: String){
        searchQuery.value = query
        beginDate.value = date
        sort.value = sorting
        requestDefault.value = articleRepository.getSearchQueryBeginDateSort(query, date, sorting)
    }

    fun getSearchQueryNewsDesk(query: String, newsDesk: String){
        searchQuery.value = query
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQueryNewsDesk(query, newsDesk)
    }

    fun getSearchQueryBeginDateNewsDesk(query: String, date: Int, newsDesk: String){
        searchQuery.value = query
        beginDate.value = date
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQueryBeginDateNewsDesk(query, date, newsDesk)
    }

    fun getSearchQuerySortNewsDesk(query: String, sorting: String, newsDesk: String){
        searchQuery.value = query
        sort.value = sorting
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQuerySortNewsDesk(query, sorting, newsDesk)
    }

    fun getSearchQueryBeginDateSortNewsDesk(query: String, date: Int, sorting: String, newsDesk: String){
        searchQuery.value = query
        beginDate.value = date
        sort.value = sorting
        news_desk.value = newsDesk
        requestDefault.value = articleRepository.getSearchQueryBeginDateSortNewsDesk(query, date, sorting, newsDesk)
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
}