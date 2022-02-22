package com.snapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.snapp.domain.interactor.SourcesUseCase
import com.snapp.domain.state.SourceViewState
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class SourcesFragmentViewModel @Inject internal constructor(
    private val sourceUseCase: SourcesUseCase
) : BaseViewModel() {

    private val _sourceParams: MutableLiveData<SourcesUseCase.SourceParams> = MutableLiveData()

    fun getSourceViewState() = currentSourceViewState

    private val currentSourceViewState: LiveData<SourceViewState> =
        _sourceParams.switchMap { params ->
            sourceUseCase.execute(params)
        }

    fun setSourceParams(params: SourcesUseCase.SourceParams) {
        if (_sourceParams.value == params)
            return
        _sourceParams.postValue(params)
    }


}
