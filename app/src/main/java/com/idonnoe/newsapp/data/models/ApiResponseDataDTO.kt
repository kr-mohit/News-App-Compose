package com.idonnoe.newsapp.data.models

data class ApiResponseDataDTO(
    val articles: List<ArticleDTO>?,
    val status: String?,
    val totalResults: Int?,
    val code: String?,
    val message: String?
)