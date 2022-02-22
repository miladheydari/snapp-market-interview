package com.snapp.domain.state

open class BaseViewState<T>(
    val viewState: ViewState,
    val data: T? = null,
    val errorMessage: String? = null,
    val throwable: Throwable? = null
)  {
    fun isLoading(): Boolean {
        return viewState==ViewState.LOADING
    }

}
