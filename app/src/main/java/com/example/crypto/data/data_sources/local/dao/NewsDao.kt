package com.example.crypto.data.data_sources.local.dao

import androidx.room.*
import com.example.crypto.data.data_sources.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    
    @Query("SELECT * FROM news ORDER BY timestamp DESC")
    fun getAllNews(): Flow<List<NewsEntity>>
    
    @Query("SELECT * FROM news WHERE id = :newsId")
    suspend fun getNewsById(newsId: String): NewsEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsList(newsList: List<NewsEntity>)
    
    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
    
    @Query("DELETE FROM news WHERE timestamp < :timestamp")
    suspend fun deleteOldNews(timestamp: Long)
    
    @Query("SELECT COUNT(*) FROM news")
    suspend fun getNewsCount(): Int
} 