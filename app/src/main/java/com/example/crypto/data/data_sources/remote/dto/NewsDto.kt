package com.example.crypto.data.data_sources.remote.dto

import com.example.crypto.domain.models.News

data class NewsDto(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String
)

fun NewsDto.toNews(): News {
    return News(
        id = url.hashCode().toString(), // Using URL hash as ID since News API doesn't provide unique IDs
        sourceName = source.name,
        author = author ?: "Unknown",
        title = title,
        description = description ?: "",
        url = url,
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt,
        content = content ?: ""
    )
} 