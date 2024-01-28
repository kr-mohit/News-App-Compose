package com.idonnoe.newsapp.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.idonnoe.newsapp.presentation.ui.composables.ArticlesLoader
import com.idonnoe.newsapp.presentation.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    onItemClick: (url: String) -> Unit
) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val articlesState = homeViewModel.articles.collectAsState()

    ArticlesLoader(articles = articlesState.value) {
        onItemClick(it)
    }
}