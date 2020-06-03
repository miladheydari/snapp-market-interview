package com.miladheydari.snappmarketandroidtest.domain

import com.miladheydari.snappmarketandroidtest.domain.model.ArticleList
import com.miladheydari.snappmarketandroidtest.domain.model.SourceList

import io.reactivex.Single
import retrofit2.http.GET

import retrofit2.http.Query

interface TestAppAPI {
    @GET("everything")

    fun getArticles(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortBy") sortBy: String = "publishedAt"
    ): Single<ArticleList>

    @GET("sources")
    fun getSources(): Single<SourceList>


}
