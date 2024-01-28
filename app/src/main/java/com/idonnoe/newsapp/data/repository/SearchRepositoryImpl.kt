package com.idonnoe.newsapp.data.repository

import com.idonnoe.newsapp.data.models.toDomainArticle
import com.idonnoe.newsapp.data.remote.NewsAPI
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI
) : SearchRepository {

    override suspend fun searchArticlesByQuery(query: String): List<Article> {
        val response = newsAPI.searchArticlesByQuery(query)
        return if (response.isSuccessful && response.body() != null) {
            if (response.body()!!.status == "ok") {
                response.body()!!.articles?.map { it.toDomainArticle() } ?: emptyList()
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

}