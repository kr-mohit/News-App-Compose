package com.idonnoe.newsapp.presentation.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.idonnoe.newsapp.presentation.navigation.BottomNavigationBar
import com.idonnoe.newsapp.presentation.navigation.NavGraph
import com.idonnoe.newsapp.presentation.ui.composables.TopBar

@Composable
fun App() {
    val navHostController = rememberNavController()
    val paddingValues = remember { mutableStateOf(PaddingValues()) }
    val currentScreen = remember { mutableStateOf(Screen.HOME_PAGE) }
    val articleUrl = remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopBar(
                title = currentScreen.value,
                url = articleUrl.value,
                onBackButtonClick = {
                    navHostController.navigateUp()
                }
            )
        },
        bottomBar = {
            if (currentScreen.value != Screen.WEB_VIEW) {
                BottomNavigationBar(navHostController = navHostController)
            }
        }
    ) {
        paddingValues.value = it
        NavGraph(
            navHostController = navHostController,
            paddingValues.value
        ) { mCurrentScreen, mUrl ->
            currentScreen.value = mCurrentScreen
            articleUrl.value = mUrl
        }
    }
}

enum class Screen(val title: String) {
    HOME_PAGE("HOME PAGE"),
    HEADLINES("HEADLINES"),
    SEARCH("SEARCH"),
    WEB_VIEW("WEB VIEW")
}