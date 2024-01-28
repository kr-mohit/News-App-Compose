package com.idonnoe.newsapp.domain.usecases

import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchArticlesByQueryUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String): List<Article> {
        return searchRepository.searchArticlesByQuery(query)
    }
}