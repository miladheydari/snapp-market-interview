package com.snapp.data.repository

import com.snapp.data.models.ArticleEntity
import io.reactivex.Single


interface ArticleRemote {

    fun getArticles(
        sources: String,
        page: Int,
        pageSize: Int
    ): Single<List<ArticleEntity>>
}
