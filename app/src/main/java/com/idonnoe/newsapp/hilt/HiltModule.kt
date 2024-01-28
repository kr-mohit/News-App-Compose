package com.idonnoe.newsapp.hilt

import com.idonnoe.newsapp.data.remote.NewsAPI
import com.idonnoe.newsapp.data.repository.HeadlinesRepositoryImpl
import com.idonnoe.newsapp.data.repository.SearchRepositoryImpl
import com.idonnoe.newsapp.domain.repository.HeadlinesRepository
import com.idonnoe.newsapp.domain.repository.SearchRepository
import com.idonnoe.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsAPI(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }


    @Provides
    fun providesHeadlinesRepository(newsAPI: NewsAPI): HeadlinesRepository {
        return HeadlinesRepositoryImpl(newsAPI)
    }

    @Provides
    fun providesSearchRepository(newsAPI: NewsAPI): SearchRepository {
        return SearchRepositoryImpl(newsAPI)
    }
}