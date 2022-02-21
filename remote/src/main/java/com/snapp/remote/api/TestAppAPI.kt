package com.snapp.remote.api

import com.snapp.remote.models.ArticleListModel
import com.snapp.remote.models.SourceListModel
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
    ): Single<ArticleListModel>

    @GET("sources")
    fun getSources(): Single<SourceListModel>
}
