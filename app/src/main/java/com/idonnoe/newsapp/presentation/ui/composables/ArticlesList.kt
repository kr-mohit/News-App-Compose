package com.idonnoe.newsapp.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.presentation.theme.ScreenBgColor

@Composable
fun ArticlesList(
    list: List<Article>,
    onItemClick: (article: Article) -> Unit
) {
    LazyColumn(
        content = {
            items(list) { item ->
                ArticleItem(article = item) {
                    onItemClick(it)
                }
            }
        },
        contentPadding = PaddingValues(10.dp, 5.dp),
        modifier = Modifier.background(ScreenBgColor)
    )
}