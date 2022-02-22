package com.snapp.domain.state

import com.snapp.domain.models.Article
import com.snapp.domain.models.Source


class SourceViewState(
    viewState: ViewState,
    data: List<Source>? = null, errorMessage: String? = null, throwable: Throwable?=null
) : BaseViewState<List<Source>>(viewState, data, errorMessage, throwable)