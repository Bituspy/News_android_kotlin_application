package com.example.crypto.domain.use_cases

import com.example.crypto.common.Resource
import com.example.crypto.data.repositories.NewsRepository
import com.example.crypto.data.data_sources.remote.dto.toNews
import com.example.crypto.domain.models.NewsDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(newsId: String): Flow<Resource<NewsDetail>> = flow {
        try {
            emit(Resource.Loading())
            val newsDto = repository.getNewsById(newsId)
            if (newsDto != null) {
                val news = newsDto.toNews()
                val newsDetail = NewsDetail(
                    id = news.id,
                    sourceName = news.sourceName,
                    author = news.author,
                    title = news.title,
                    description = news.description,
                    url = news.url,
                    urlToImage = news.urlToImage,
                    publishedAt = news.publishedAt,
                    content = news.content
                )
                emit(Resource.Success(newsDetail))
            } else {
                emit(Resource.Error("News article not found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Http error"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection"))
        }
    }
} 