package com.miladheydari.snappmarketandroidtest.di

import android.content.Context
import com.snapp.cache.AppDatabase
import com.snapp.cache.ArticleLocalDataSource
import com.snapp.cache.SourceLocalDataSource
import com.snapp.cache.dao.ArticlesDao
import com.snapp.cache.dao.SourceDao
import com.snapp.data.repository.ArticleCache
import com.snapp.data.repository.SourceCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideArticleDao(db: AppDatabase): ArticlesDao {
        return db.articlesDao()
    }

    @Provides
    @Singleton
    fun provideSourceDao(db: AppDatabase): SourceDao {
        return db.sourceDao()
    }


    @Provides
    @Singleton
    fun provideArticleCache(impl: ArticleLocalDataSource): ArticleCache =
        impl

    @Provides
    @Singleton
    fun provideSourceCache(impl: SourceLocalDataSource): SourceCache =
        impl
}
