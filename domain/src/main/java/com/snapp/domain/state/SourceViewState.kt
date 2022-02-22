package com.snapp.domain.state

import com.snapp.domain.models.Article
import com.snapp.domain.models.Source


class SourceViewState(
    viewState: ViewState,
    data: List<Source>?, errorMessage: String?, throwable: Throwable?
) : BaseViewState<List<Source>>(viewState, data, errorMessage, throwable){
    fun isLoading(): Boolean {
       return viewState!=ViewState.LOADING
    }

}