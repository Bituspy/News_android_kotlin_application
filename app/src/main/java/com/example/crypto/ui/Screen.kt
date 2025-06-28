package com.example.crypto.ui

sealed class Screen(val route: String) {
    data object NewsListScreen : Screen("news_list_screen")
    data object NewsDetailScreen: Screen("news_detail_screen/{${com.example.crypto.common.Constants.PARAM_NEWS_ID}}")
}