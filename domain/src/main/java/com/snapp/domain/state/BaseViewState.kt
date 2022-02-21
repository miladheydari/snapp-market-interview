package com.snapp.domain.state

import com.snapp.domain.utils.UiAwareModel

open class BaseViewState<T>(
    val viewState: ViewState,
    val data: T? = null,
    val errorMessage: String? = null,
    val throwable: Throwable? = null
) : UiAwareModel() {
}
