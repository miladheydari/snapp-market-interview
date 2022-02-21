package com.snapp.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.snapp.domain.state.ArticleViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesFragmentViewModel @Inject internal constructor(
    private val articleUseCase: com.snapp.domain.interactor.ArticleUseCase
) : BaseViewModel() {

    private val _articleParams: MutableLiveData<com.snapp.domain.interactor.ArticleUseCase.ArticleParams> = MutableLiveData()

    fun getArticleViewState() = currentArticleViewState

    private val currentArticleViewState: LiveData<ArticleViewState> =
        _articleParams.switchMap { params ->
            articleUseCase.execute(params)
        }

    fun setArticleParams(params: com.snapp.domain.interactor.ArticleUseCase.ArticleParams) {
        if (_articleParams.value == params)
            return
        _articleParams.postValue(params)
    }


}
