package com.snapp.remote

import com.snapp.data.models.ArticleEntity
import com.snapp.data.repository.ArticleRemote
import com.snapp.remote.api.TestAppAPI
import com.snapp.remote.mappers.ArticleEntityMapper
import io.reactivex.Single
import javax.inject.Inject

class ArticleRemoteImpl @Inject constructor(
    private val api: TestAppAPI,
    private val articleEntityMapper: ArticleEntityMapper
) : ArticleRemote {


    override fun getArticles(
        sources: String,
        page: Int,
        pageSize: Int
    ): Single<List<ArticleEntity>> {
        return api.getArticles(sources, page, pageSize).map {
            it.articles.map(articleEntityMapper::mapFromModel)
        }
    }
}