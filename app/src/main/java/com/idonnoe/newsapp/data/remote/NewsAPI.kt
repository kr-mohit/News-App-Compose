package com.idonnoe.newsapp.data.remote

import com.idonnoe.newsapp.data.models.ApiResponseDataDTO
import com.idonnoe.newsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsAPI {

    // GET https://newsapi.org/v2/top-headlines?country=us&apiKey=46c5ba9f37784ad79665d262f96c7ffc

    @GET("/v2/top-headlines")
    @Headers("X-Api-Key: ${Constants.API_KEY}")
    suspend fun getHeadlinesByCountry(@Query("country") country: String): Response<ApiResponseDataDTO>

    // GET https://newsapi.org/v2/top-headlines?q=trump&apiKey=46c5ba9f37784ad79665d262f96c7ffc

    @GET("/v2/top-headlines")
    @Headers("X-Api-Key: ${Constants.API_KEY}")
    suspend fun getHeadlinesByQuery(@Query("q") query: String): Response<ApiResponseDataDTO>

    // GET https://newsapi.org/v2/everything?q=bitcoin&apiKey=46c5ba9f37784ad79665d262f96c7ffc

    @GET("/v2/everything")
    @Headers("X-Api-Key: ${Constants.API_KEY}")
    suspend fun searchArticlesByQuery(@Query("q") query: String): Response<ApiResponseDataDTO>

}