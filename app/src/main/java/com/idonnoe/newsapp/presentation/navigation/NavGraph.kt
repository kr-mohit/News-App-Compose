package com.idonnoe.newsapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.idonnoe.newsapp.presentation.ui.screens.HeadlinesScreen
import com.idonnoe.newsapp.presentation.ui.screens.HomeScreen
import com.idonnoe.newsapp.presentation.ui.screens.Screen
import com.idonnoe.newsapp.presentation.ui.screens.SearchScreen
import com.idonnoe.newsapp.presentation.ui.screens.WebViewScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    onScreenChange: (currentScreen: Screen, url: String?) -> Unit
) {

    fun navigateToWebViewScreen(url: String) {
        navHostController.currentBackStackEntry?.savedStateHandle?.set("url", url)
        navHostController.navigate(route = NavigationItem.WebView.route)
    }

    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(route = NavigationItem.Home.route) {
            HomeScreen { url ->
                navigateToWebViewScreen(url)
            }
            onScreenChange(Screen.HOME_PAGE, null)
        }
        composable(route = NavigationItem.Headlines.route) {
            HeadlinesScreen { url ->
                navigateToWebViewScreen(url)
            }
            onScreenChange(Screen.HEADLINES, null)
        }
        composable(route = NavigationItem.Search.route) {
            SearchScreen { url ->
                navigateToWebViewScreen(url)
            }
            onScreenChange(Screen.SEARCH, null)
        }
        composable(route = NavigationItem.WebView.route) {
            val url = navHostController.previousBackStackEntry?.savedStateHandle?.get<String>("url")
                ?: "no_url"
            WebViewScreen(url)
            onScreenChange(Screen.WEB_VIEW, url)
        }
    }
}