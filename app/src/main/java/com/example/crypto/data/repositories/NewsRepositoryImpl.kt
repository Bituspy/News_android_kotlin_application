package com.example.crypto.data.repositories

import com.example.crypto.data.data_sources.local.dao.NewsDao
import com.example.crypto.data.data_sources.local.entity.toNewsEntity
import com.example.crypto.data.data_sources.remote.NewsApi
import com.example.crypto.data.data_sources.remote.dto.NewsDto
import com.example.crypto.data.data_sources.remote.dto.NewsResponseDto
import com.example.crypto.data.data_sources.remote.dto.toNews
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getNews(query: String): NewsResponseDto {
        return try {
            // Fetch from remote API
            val response = api.getNews(query = query)
            
            // Cache the articles locally
            val newsEntities = response.articles.map { it.toNews().toNewsEntity() }
            newsDao.insertNewsList(newsEntities)
            
            response
        } catch (e: Exception) {
            // If API fails, return empty response
            NewsResponseDto(
                status = "error",
                totalResults = 0,
                articles = emptyList()
            )
        }
    }

    override suspend fun getNewsById(newsId: String): NewsDto? {
        // First try to get from local database
        val newsEntity = newsDao.getNewsById(newsId)
        return newsEntity?.let { entity ->
            NewsDto(
                source = com.example.crypto.data.data_sources.remote.dto.Source(
                    id = null,
                    name = entity.sourceName
                ),
                author = entity.author,
                title = entity.title,
                description = entity.description,
                url = entity.url,
                urlToImage = entity.urlToImage,
                publishedAt = entity.publishedAt,
                content = entity.content
            )
        }
    }
    
    suspend fun getCachedNews(): List<com.example.crypto.domain.models.News> {
        val entities = newsDao.getAllNews().first()
        return entities.map { it.toNews() }
    }
    
    suspend fun clearOldCache() {
        // Clear news older than 24 hours
        val oneDayAgo = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        newsDao.deleteOldNews(oneDayAgo)
    }
} 