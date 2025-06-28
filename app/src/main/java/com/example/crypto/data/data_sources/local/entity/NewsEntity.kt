package com.example.crypto.data.data_sources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.crypto.domain.models.News

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    val id: String,
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun toNews(): News {
        return News(
            id = id,
            sourceName = sourceName,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content
        )
    }
}

fun News.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        sourceName = sourceName,
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
} 