package com.example.crypto.data.repositories

import com.example.crypto.data.data_sources.remote.dto.NewsDto
import com.example.crypto.data.data_sources.remote.dto.NewsResponseDto

interface NewsRepository {
    suspend fun getNews(query: String = "technology"): NewsResponseDto
    suspend fun getNewsById(newsId: String): NewsDto?
} 