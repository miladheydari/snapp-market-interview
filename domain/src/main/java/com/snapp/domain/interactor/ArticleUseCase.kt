package com.snapp.domain.interactor

import androidx.lifecycle.LiveData


import com.snapp.domain.state.ArticleViewState
import com.snapp.domain.repository.ArticleRepository
import com.snapp.domain.utils.UseCaseLiveData
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
        )

    }

    class ArticleParams(
        val sourceId: String,
        val page: Int,
        val pageSize: Int,
        val fetchRequired: Boolean
    ) : Params()
}
