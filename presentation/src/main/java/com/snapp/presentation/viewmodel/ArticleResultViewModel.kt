package com.snapp.presentation.viewmodel

import androidx.databinding.ObservableField
import com.snapp.domain.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleResultViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<Article>()
}