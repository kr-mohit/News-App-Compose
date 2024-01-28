package com.idonnoe.newsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {

    object Home : NavigationItem("home", "Home", Icons.Default.Home)
    object Headlines : NavigationItem("headlines", "Headlines", Icons.Default.Star)
    object Search : NavigationItem("search", "Search", Icons.Default.Search)
    object WebView : NavigationItem("webview", "WebView", Icons.Default.ExitToApp)
}