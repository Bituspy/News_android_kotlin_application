package com.example.crypto.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.crypto.common.Constants
import com.example.crypto.ui.news_detail.ui_elements.NewsDetailScreen
import com.example.crypto.ui.news_list.ui_elements.NewsListScreen
import com.example.crypto.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.composable
import com.example.crypto.domain.models.News

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ) {
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            NewsListScreen(onItemClick = { news ->
                                navController.navigate(Screen.NewsDetailScreen.route.replace("{${Constants.PARAM_NEWS_ID}}", news.id))
                            })
                        }
                        composable(
                            route = Screen.NewsDetailScreen.route
                        ) {
                            NewsDetailScreen()
                        }
                    }
                }
            }
        }
    }
}