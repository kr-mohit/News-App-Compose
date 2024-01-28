package com.idonnoe.newsapp.data.models

import com.idonnoe.newsapp.domain.models.Article

data class ArticleDTO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDTO?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun ArticleDTO.toDomainArticle(): Article {
    return Article(
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        source = this.source?.name ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: "",
    )
}