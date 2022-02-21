package com.miladheydari.snappmarketandroidtest.di

import com.miladheydari.snappmarketandroidtest.BuildConfig
import com.miladheydari.snappmarketandroidtest.core.Constants
import com.snapp.data.repository.ArticleRemote
import com.snapp.data.repository.SourceRemote
import com.snapp.remote.ArticleRemoteImpl
import com.snapp.remote.SourcesRemoteImpl
import com.snapp.remote.api.ServiceFactory
import com.snapp.remote.api.TestAppAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): TestAppAPI =
        ServiceFactory.create(BuildConfig.DEBUG, Constants.NetworkService.BASE_URL)

    @Singleton
    @Provides
    fun provideArticleRemote(articleRemoteImpl: ArticleRemoteImpl): ArticleRemote = articleRemoteImpl

    @Singleton
    @Provides
    fun provideSourceRemote(sourcesRemoteImpl: SourcesRemoteImpl):SourceRemote = sourcesRemoteImpl
}