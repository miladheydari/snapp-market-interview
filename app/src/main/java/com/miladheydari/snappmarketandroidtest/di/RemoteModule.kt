package com.miladheydari.snappmarketandroidtest.di

import com.miladheydari.snappmarketandroidtest.BuildConfig
import com.miladheydari.snappmarketandroidtest.core.Constants
import com.snapp.data.repository.ArticleRemote
import com.snapp.data.repository.SourceRemote
import com.snapp.remote.ArticleRemoteImpl
import com.snapp.remote.SourcesRemoteImpl
import com.snapp.remote.api.TestAppAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {


    fun create(isDebug: Boolean, baseUrl: String): TestAppAPI {
        val retrofit = createRetrofit(isDebug, baseUrl)
        return retrofit.create(TestAppAPI::class.java)
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient(createLoggingInterceptor(isDebug)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.addHeader("X-Api-Key", Constants.NetworkService.CLIENT_SECRET)
                val response = it.proceed(requestBuilder.build())

                when (response.code) {
                    400 -> {
                        Timber.d("400 - Bad Request")
                    }
                    401 -> {
                        Timber.d("401 - Unauthorized - refresh token")
                    }
                    403 -> {
                        Timber.d("403 - Forbidden")
                    }
                    404 -> {
                        Timber.d("404 - Not Found - URL")
                    }
                    405 -> {
                        Timber.d("405 - Method Not Allowed - DELETE - GET")
                    }
                    500 -> {
                        Timber.d("500 - Internal Server ErrorT")
                    }
                    else -> {
                        Timber.d("Server error - ${response.message}")
                    }
                }

                response

            }
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private const val OK_HTTP_TIMEOUT = 60L


    @Singleton
    @Provides
    fun provideApiService(): TestAppAPI =
        create(BuildConfig.DEBUG, Constants.NetworkService.BASE_URL)

    @Singleton
    @Provides
    fun provideArticleRemote(articleRemoteImpl: ArticleRemoteImpl): ArticleRemote =
        articleRemoteImpl

    @Singleton
    @Provides
    fun provideSourceRemote(sourcesRemoteImpl: SourcesRemoteImpl): SourceRemote = sourcesRemoteImpl
}