package com.idonnoe.newsapp.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.usecases.SearchArticlesByQueryUseCase
import com.idonnoe.newsapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArticlesByQueryUseCase: SearchArticlesByQueryUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<Response<List<Article>>>(Response.Success(emptyList()))
    val articles: StateFlow<Response<List<Article>>>
        get() = _articles

    fun searchArticlesByQuery(query: String) {
        viewModelScope.launch {
            _articles.emit(Response.Loading())
            _articles.emit(Response.Success(searchArticlesByQueryUseCase(query)))
        }
    }
}