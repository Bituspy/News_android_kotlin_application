package com.example.crypto.data.data_sources.remote

import com.example.crypto.common.Constants
import com.example.crypto.data.data_sources.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String = "technology",
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("pageSize") pageSize: Int = 20,
        @Query("language") language: String = "en"
    ): NewsResponseDto
} 