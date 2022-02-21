package com.miladheydari.snappmarketandroidtest.di

import com.snapp.data.ArticleRepositoryImpl
import com.snapp.data.SourceRepositoryImpl
import com.snapp.domain.repository.ArticleRepository
import com.snapp.domain.repository.SourceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun provideArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl
    ): ArticleRepository = articleRepositoryImpl

    @Singleton
    @Provides
    fun provideSourceRepository(
        sourceRepositoryImpl: SourceRepositoryImpl
    ): SourceRepository = sourceRepositoryImpl
}