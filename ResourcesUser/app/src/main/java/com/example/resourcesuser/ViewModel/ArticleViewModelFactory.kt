package com.example.resourcesuser.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.resourcesuser.Repository.ArticleRepository

class ArticleViewModelFactory (private val articleRepository: ArticleRepository)
    : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) = ArticleViewModel(articleRepository) as T
}