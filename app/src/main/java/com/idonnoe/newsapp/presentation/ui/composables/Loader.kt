package com.idonnoe.newsapp.presentation.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Loader(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            trackColor = Color.Gray,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}