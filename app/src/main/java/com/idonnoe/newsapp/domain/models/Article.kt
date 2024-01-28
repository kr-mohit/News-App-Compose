package com.idonnoe.newsapp.domain.models

data class Article(
    val urlToImage: String,
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val publishedAt: String
)
