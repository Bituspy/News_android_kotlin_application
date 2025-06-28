package com.example.crypto.ui.news_detail.state

import com.example.crypto.domain.models.NewsDetail

data class NewsDetailState(
    val newsDetail: NewsDetail? = null,
    val isLoading: Boolean = false,
    val error: String = ""
) 