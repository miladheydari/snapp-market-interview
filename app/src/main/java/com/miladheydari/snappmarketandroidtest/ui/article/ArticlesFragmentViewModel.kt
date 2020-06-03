package com.miladheydari.snappmarketandroidtest.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.miladheydari.snappmarketandroidtest.core.BaseViewModel

import com.miladheydari.snappmarketandroidtest.domain.usecase.ArticleUseCase

import javax.inject.Inject

class ArticlesFragmentViewModel @Inject internal constructor(
    private val articleUseCase: ArticleUseCase
) : BaseViewModel() {

    private val _articleParams: MutableLiveData<ArticleUseCase.ArticleParams> = MutableLiveData()

    fun getArticleViewState() = currentArticleViewState

    private val currentArticleViewState: LiveData<ArticleViewState> =
        _articleParams.switchMap { params ->
            articleUseCase.execute(params)
        }

    fun setArticleParams(params: ArticleUseCase.ArticleParams) {
        if (_articleParams.value == params)
            return
        _articleParams.postValue(params)
    }


    fun getArticleTotalResult(): Int? {
        return getArticleViewState().value?.data?.size
    }
}
