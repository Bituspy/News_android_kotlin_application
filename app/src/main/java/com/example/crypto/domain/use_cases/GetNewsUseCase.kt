package com.example.crypto.domain.use_cases

import com.example.crypto.common.Resource
import com.example.crypto.data.repositories.NewsRepository
import com.example.crypto.data.data_sources.remote.dto.toNews
import com.example.crypto.domain.models.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String = "technology"): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())
            val newsResponse = repository.getNews(query)
            val news = newsResponse.articles.map { it.toNews() }
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Http error"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection"))
        }
    }
} 