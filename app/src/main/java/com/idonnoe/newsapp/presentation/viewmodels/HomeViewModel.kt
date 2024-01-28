package com.idonnoe.newsapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.usecases.GetHeadlinesByCountryUseCase
import com.idonnoe.newsapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHeadlinesByCountryUseCase: GetHeadlinesByCountryUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<Response<List<Article>>>(Response.Loading())
    val articles: StateFlow<Response<List<Article>>>
        get() = _articles

    init {
        getHomePageArticles()
    }

    private fun getHomePageArticles() {
        viewModelScope.launch {
            _articles.emit(Response.Loading())
            _articles.emit(Response.Success(getHeadlinesByCountryUseCase("in")))
        }
    }
}