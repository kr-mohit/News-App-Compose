package com.idonnoe.newsapp.domain.usecases

import com.idonnoe.newsapp.domain.models.Article
import com.idonnoe.newsapp.domain.repository.HeadlinesRepository
import javax.inject.Inject

class GetHeadlinesByCountryUseCase @Inject constructor(
    private val headlinesRepository: HeadlinesRepository
) {

    suspend operator fun invoke(country: String): List<Article> {
        return headlinesRepository.getHeadlinesByCountry(country)
    }
}