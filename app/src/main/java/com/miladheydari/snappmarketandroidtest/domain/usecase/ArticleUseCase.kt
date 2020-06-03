package com.miladheydari.snappmarketandroidtest.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.miladheydari.snappmarketandroidtest.domain.model.Article

import com.miladheydari.snappmarketandroidtest.repo.ArticleRepository
import com.miladheydari.snappmarketandroidtest.ui.article.ArticleViewState


import com.miladheydari.snappmarketandroidtest.utils.UseCaseLiveData
import com.miladheydari.snappmarketandroidtest.utils.domain.Resource
import javax.inject.Inject

class ArticleUseCase @Inject internal constructor(
    private val repository: ArticleRepository
) : UseCaseLiveData<ArticleViewState, ArticleUseCase.ArticleParams,
        ArticleRepository>() {

    override fun getRepository(): ArticleRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: ArticleParams?): LiveData<ArticleViewState> {
        return repository.loadArticleBySourceId(
            sourceId = params?.sourceId ?: "",

            page = params?.page ?: 0,
            pageSize = params?.pageSize ?: 0,
            fetchRequired = params?.fetchRequired ?: true
        ).map {
            onArticleResultReady(it)
        }
    }

    private fun onArticleResultReady(resource: Resource<List<Article>>): ArticleViewState {
        return ArticleViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class ArticleParams(
        val sourceId: String,

        val page: Int,
        val pageSize: Int,
        val fetchRequired: Boolean
    ) : Params()
}
