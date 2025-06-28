package com.example.crypto.di

import android.app.Application
import androidx.room.Room
import com.example.crypto.common.Constants
import com.example.crypto.data.data_sources.local.NewsLocalDB
import com.example.crypto.data.data_sources.local.dao.NewsDao
import com.example.crypto.data.repositories.NewsRepository
import com.example.crypto.data.repositories.NewsRepositoryImpl
import com.example.crypto.data.data_sources.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsLocalDB(app: Application): NewsLocalDB {
        return NewsLocalDB.getInstance(app)
    }

    @Provides
    @Singleton
    fun provideNewsDao(db: NewsLocalDB): NewsDao {
        return db.newsDao
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(api, newsDao)
    }
}