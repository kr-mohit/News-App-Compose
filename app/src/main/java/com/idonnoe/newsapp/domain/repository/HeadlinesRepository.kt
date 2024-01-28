package com.idonnoe.newsapp.domain.repository

import com.idonnoe.newsapp.domain.models.Article

interface HeadlinesRepository {

    suspend fun getHeadlinesByCountry(country: String): List<Article>

    suspend fun getHeadlinesByQuery(query: String): List<Article>

}