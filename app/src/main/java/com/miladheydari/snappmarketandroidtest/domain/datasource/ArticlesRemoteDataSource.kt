package com.miladheydari.snappmarketandroidtest.domain.datasource

import com.miladheydari.snappmarketandroidtest.domain.TestAppAPI
import com.miladheydari.snappmarketandroidtest.domain.model.ArticleList
import io.reactivex.Single
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(private val api: TestAppAPI) {

    fun getArticles(
        sources: String,

        page: Int,
        pageSize: Int
    ): Single<ArticleList> {

        return api.getArticles(sources, page, pageSize)
    }
}
