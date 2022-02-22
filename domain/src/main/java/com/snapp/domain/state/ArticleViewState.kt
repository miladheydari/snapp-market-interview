package com.snapp.domain.state

import com.snapp.domain.models.Article


class ArticleViewState(
    viewState: ViewState,
    data: List<Article>?=null, errorMessage: String?=null, throwable: Throwable?=null
) : BaseViewState<List<Article>>(viewState, data, errorMessage, throwable)