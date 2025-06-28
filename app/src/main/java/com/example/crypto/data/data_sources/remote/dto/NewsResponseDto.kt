package com.example.crypto.data.data_sources.remote.dto

data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsDto>
) 