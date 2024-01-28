package com.idonnoe.newsapp.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idonnoe.newsapp.domain.models.TopicListData
import com.idonnoe.newsapp.presentation.theme.CardStrokeColor
import com.idonnoe.newsapp.presentation.theme.DividerColor
import com.idonnoe.newsapp.presentation.theme.ScreenBgColor
import com.idonnoe.newsapp.presentation.theme.TextColor
import com.idonnoe.newsapp.presentation.ui.composables.ArticlesLoader
import com.idonnoe.newsapp.presentation.ui.viewmodels.HeadlinesViewModel

@Composable
fun HeadlinesScreen(
    onItemClick: (url: String) -> Unit
) {

    val headlineViewModel: HeadlinesViewModel = hiltViewModel()
    val isFirstTime = headlineViewModel.isFirstTime.collectAsState()
    val articlesState = headlineViewModel.articles.collectAsState()
    val topicListData = headlineViewModel.topics.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBgColor)
    ) {
        TopicsList(
            topicList = topicListData.value ?: emptyList(),
            isFirstTime = isFirstTime.value
        ) {
            headlineViewModel.getHeadlinesByQuery(it)
        }
    }
    if (!isFirstTime.value) {
        ArticlesLoader(articles = articlesState.value) {
            onItemClick(it)
        }
    }
}

@Composable
fun TopicsList(
    topicList: List<TopicListData>,
    isFirstTime: Boolean,
    onTopicClick: (topic: String) -> Unit
) {

    Log.d("mohit", "TopicsList Composition")

    if (isFirstTime) {
        LazyColumn(
            content = {
                items(topicList) { item ->
                    TopicItem(item) {
                        onTopicClick(it)
                    }
                }
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .fillMaxWidth()
        )
    } else {
        Spacer(modifier = Modifier.height(5.dp))
        LazyRow(
            content = {
                items(topicList) { item ->
                    TopicItem(item) {
                        onTopicClick(it)
                    }
                }
            },
            modifier = Modifier.padding(10.dp, 5.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Divider(thickness = 2.dp, color = DividerColor)
    }
}

@Composable
fun TopicItem(data: TopicListData, onItemClick: (text: String) -> Unit) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = data.color
        ),
        border = BorderStroke(1.dp, CardStrokeColor),
        modifier = Modifier
            .clickable { onItemClick(data.topic) }
            .padding(2.dp, 2.dp)
    ) {
        Text(
            text = data.topic,
            fontSize = 20.sp,
            color = TextColor,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Press() {
    TopicsList(topicList = getDummyTopicsList(), false) {

    }
}

fun getDummyTopicsList(): List<TopicListData> {
    val topicList = listOf(
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
    return topicList.map { TopicListData(it, getRandomColor()) }
}

fun getRandomColor(): Color {
    return Color(
        red = (0..255).random(),
        green = (0..255).random(),
        blue = (0..255).random(),
        alpha = 100
    )
}