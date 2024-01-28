package com.idonnoe.newsapp.data.repository

import com.idonnoe.newsapp.data.models.ApiResponseDataDTO
import com.idonnoe.newsapp.data.models.toDomainArticle
import com.idonnoe.newsapp.data.remote.NewsAPI
import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.repository.HeadlinesRepository
import javax.inject.Inject

class HeadlinesRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI
) : HeadlinesRepository {

    override suspend fun getHeadlinesByCountry(country: String): List<Article> {
        val response = newsAPI.getHeadlinesByCountry(country)
        return if (response.isSuccessful && response.body() != null) {
            convertApiResponseToArticles(response.body()!!)
        } else {
            emptyList()
        }
    }

    override suspend fun getHeadlinesByQuery(query: String): List<Article> {
        val response = newsAPI.getHeadlinesByQuery(query)
        return if (response.isSuccessful && response.body() != null) {
            convertApiResponseToArticles(response.body()!!)
        } else {
            emptyList()
        }
    }

    private fun convertApiResponseToArticles(data: ApiResponseDataDTO): List<Article> {
        return if (data.status == "ok") {
            data.articles?.map { it.toDomainArticle() } ?: emptyList()
        } else {
            emptyList()
        }

    }
}