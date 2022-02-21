package com.snapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.snapp.domain.state.SourceViewState

import javax.inject.Inject

class SourcesFragmentViewModel @Inject internal constructor(
    private val sourceUseCase: com.snapp.domain.interactor.SourcesUseCase
) : BaseViewModel() {

    private val _sourceParams: MutableLiveData<com.snapp.domain.interactor.SourcesUseCase.SourceParams> = MutableLiveData()

    fun getSourceViewState() = currentSourceViewState

    private val currentSourceViewState: LiveData<SourceViewState> =
        _sourceParams.switchMap { params ->
            sourceUseCase.execute(params)
        }

    fun setSourceParams(params: com.snapp.domain.interactor.SourcesUseCase.SourceParams) {
        if (_sourceParams.value == params)
            return
        _sourceParams.postValue(params)
    }


}
