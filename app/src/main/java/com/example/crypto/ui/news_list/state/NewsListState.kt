package com.example.crypto.ui.news_list.state

import com.example.crypto.domain.models.News

data class NewsListState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
) 