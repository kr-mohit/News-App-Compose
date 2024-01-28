package com.idonnoe.newsapp.presentation.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.presentation.theme.CardBgColor
import com.idonnoe.newsapp.presentation.theme.CardStrokeColor
import com.idonnoe.newsapp.presentation.theme.TextColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(
    article: Article,
    onItemClick: (article: Article) -> Unit
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CardBgColor
        ),
        border = BorderStroke(1.dp, CardStrokeColor),
        modifier = Modifier
            .clickable { onItemClick(article) }
            .padding(0.dp, 5.dp)
    ) {
        Column {
            GlideImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = article.title,
                fontSize = 20.sp,
                color = TextColor,
                modifier = Modifier.padding(5.dp, 0.dp)
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }
    }
}