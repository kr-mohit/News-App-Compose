package com.idonnoe.newsapp.presentation.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.sp
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.presentation.theme.IconColor
import com.idonnoe.newsapp.presentation.theme.ScreenBgColor
import com.idonnoe.newsapp.presentation.theme.TextColor
import com.idonnoe.newsapp.utils.Response

@Composable
fun ArticlesLoader(articles: Response<List<Article>>, onItemClick: (url: String) -> Unit) {

    when (articles) {
        is Response.Error -> {
            SnackbarHost(
                hostState = SnackbarHostState(),
                snackbar = {
                    Snackbar(
                        content = { Text(text = articles.error ?: "Unknown Error") },
                        action = {
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Dismiss")
                            }
                        }
                    )
                }
            )
        }

        is Response.Loading -> {
            Loader(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ScreenBgColor)
            )
        }

        is Response.Success -> {
            if (articles.data.isNullOrEmpty()) {
                EmptyListText()
            } else {
                ArticlesList(list = articles.data) {
                    onItemClick(it.url)
                }
            }
        }
    }
}

@Composable
fun EmptyListText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            colorFilter = ColorFilter.tint(IconColor)
        )
        Text(
            text = "No News Available...",
            color = TextColor,
            fontSize = 18.sp
        )
    }
}