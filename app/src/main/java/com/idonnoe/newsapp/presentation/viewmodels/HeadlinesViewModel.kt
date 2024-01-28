package com.idonnoe.newsapp.presentation.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.models.TopicListData
import com.idonnoe.newsapp.domain.usecases.GetHeadlinesByQueryUseCase
import com.idonnoe.newsapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val getHeadlinesByQueryUseCase: GetHeadlinesByQueryUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<Response<List<Article>>>(Response.Success(emptyList()))
    val articles: StateFlow<Response<List<Article>>>
        get() = _articles

    val isFirstTime = MutableStateFlow(true)

    private val _topics = MutableLiveData<List<TopicListData>>(emptyList())
    val topics: LiveData<List<TopicListData>>
        get() = _topics

    init {
        val topicList = getTopicsList()
        _topics.postValue(topicList.map { TopicListData(it, getRandomColor()) })
    }

    fun getHeadlinesByQuery(query: String) {
        isFirstTime.value = false
        viewModelScope.launch {
            _articles.emit(Response.Loading())
            _articles.emit(Response.Success(getHeadlinesByQueryUseCase(query)))
        }
    }

    private fun getRandomColor(): Color {
        return Color(
            red = (0..255).random(),
            green = (0..255).random(),
            blue = (0..255).random(),
            alpha = 100
        )
    }

    private fun getTopicsList(): List<String> {
        return listOf(
            "Bitcoin",
            "Hindu",
            "Delhi",
            "Syria vs India",
            "HDFC Bank",
            "Ram Bhajan",
            "Groww",
            "Republic Day 2024",
            "Live",
            "Youtube",
            "Virat Kohli",
            "Pran Pratishtha",
            "Nifty 50"
        )
    }
}