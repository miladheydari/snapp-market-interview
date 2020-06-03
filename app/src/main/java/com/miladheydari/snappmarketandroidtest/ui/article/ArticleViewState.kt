package com.miladheydari.snappmarketandroidtest.ui.article

import com.miladheydari.snappmarketandroidtest.domain.model.Article
import com.miladheydari.snappmarketandroidtest.utils.domain.Status

class ArticleViewState(
    val status: Status,
    val error: String? = null,
    val data: List<Article>? = null
) {
    fun getArticles() = data

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error

    fun shouldShowErrorMessage() = error != null
}
