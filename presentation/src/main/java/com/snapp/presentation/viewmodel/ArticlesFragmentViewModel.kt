package com.snapp.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.snapp.domain.interactor.ArticleUseCase
import com.snapp.domain.state.ArticleViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesFragmentViewModel @Inject internal constructor(
    private val articleUseCase: ArticleUseCase
) : BaseViewModel() {

    private val _articleParams: MutableLiveData<ArticleUseCase.ArticleParams> = MutableLiveData()

    fun getArticleViewState() = currentArticleViewState

    private val currentArticleViewState: LiveData<ArticleViewState> =
        _articleParams.switchMap { params ->
            articleUseCase.execute(params)
        }

    fun setArticleParams(params: ArticleUseCase.ArticleParams) {
        if (_articleParams.value == params)
            return
        _articleParams.postValue(params)
    }


}
