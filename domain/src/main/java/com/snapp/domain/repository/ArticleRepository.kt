package com.snapp.domain.repository

import androidx.lifecycle.LiveData
import com.snapp.domain.state.ArticleViewState


interface ArticleRepository {


    fun loadArticleBySourceId(
        sourceId: String,
        page: Int,
        pageSize: Int,
        fetchRequired: Boolean
    ): LiveData<ArticleViewState>
}
