package com.example.crypto.data.data_sources.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.crypto.data.data_sources.local.dao.NewsDao
import com.example.crypto.data.data_sources.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1
)
abstract class NewsLocalDB : RoomDatabase() {
    abstract val newsDao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsLocalDB? = null

        fun getInstance(context: Context): NewsLocalDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsLocalDB::class.java,
                    "news_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 