package com.idonnoe.newsapp.domain.repository

import com.idonnoe.newsapp.domain.models.Article

interface SearchRepository {

    suspend fun searchArticlesByQuery(query: String): List<Article>

}