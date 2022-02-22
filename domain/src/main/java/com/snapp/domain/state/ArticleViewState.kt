package com.snapp.domain.state

import com.snapp.domain.models.Article


class ArticleViewState(
    viewState: ViewState,
    data: List<Article>?, errorMessage: String?, throwable: Throwable?
) : BaseViewState<List<Article>>(viewState, data, errorMessage, throwable){

    fun isLoading(): Boolean {
       return viewState!=ViewState.LOADING
    }
}