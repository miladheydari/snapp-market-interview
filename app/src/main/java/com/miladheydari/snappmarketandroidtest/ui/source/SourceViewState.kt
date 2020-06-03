package com.miladheydari.snappmarketandroidtest.ui.source

import com.miladheydari.snappmarketandroidtest.domain.model.Source
import com.miladheydari.snappmarketandroidtest.utils.domain.Status

class SourceViewState(
    val status: Status,
    val error: String? = null,
    val data: List<Source>? = null
) {
    fun getSources() = data

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error

    fun shouldShowErrorMessage() = error != null
}
