package com.idonnoe.newsapp.presentation.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idonnoe.newsapp.presentation.theme.DividerColor
import com.idonnoe.newsapp.presentation.theme.IconColor
import com.idonnoe.newsapp.presentation.theme.TextColor
import com.idonnoe.newsapp.presentation.theme.TopBarColor
import com.idonnoe.newsapp.presentation.ui.screens.Screen

@Composable
fun TopBar(title: Screen, url: String? = null, onBackButtonClick: () -> Unit) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.background(TopBarColor)
    ) {
        Row {
            if (title == Screen.WEB_VIEW) {
                Image(
                    imageVector = Icons.Default.ArrowBack,
                    colorFilter = ColorFilter.tint(IconColor),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(10.dp)
                        .clickable {
                            onBackButtonClick()
                        }
                )
            }
            Text(
                text = title.title,
                color = TextColor,
                fontSize = 24.sp,
                modifier = Modifier
                    .weight(1F)
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )
            if (title == Screen.WEB_VIEW) {
                Image(
                    imageVector = Icons.Default.ExitToApp,
                    colorFilter = ColorFilter.tint(IconColor),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(10.dp)
                        .clickable {
                            url?.let { url ->
                                Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
                                    context.startActivity(it)
                                }
                            }
                        }
                )
            }
        }
        Divider(
            thickness = 2.dp,
            color = DividerColor
        )
    }
}