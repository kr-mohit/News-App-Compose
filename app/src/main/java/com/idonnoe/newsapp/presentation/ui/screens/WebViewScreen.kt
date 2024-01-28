package com.idonnoe.newsapp.presentation.ui.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.idonnoe.newsapp.presentation.theme.ScreenBgColor

@Composable
fun WebViewScreen(url: String) {
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBgColor),
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        update = { it.loadUrl(url) }
    )
}