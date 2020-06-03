package com.miladheydari.snappmarketandroidtest.ui.article.result

import androidx.databinding.ObservableField
import com.miladheydari.snappmarketandroidtest.core.BaseViewModel
import com.miladheydari.snappmarketandroidtest.domain.model.Article
import javax.inject.Inject

class ArticleResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<Article>()
}