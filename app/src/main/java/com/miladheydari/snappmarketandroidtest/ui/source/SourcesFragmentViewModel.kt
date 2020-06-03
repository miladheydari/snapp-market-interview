package com.miladheydari.snappmarketandroidtest.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.miladheydari.snappmarketandroidtest.core.BaseViewModel


import com.miladheydari.snappmarketandroidtest.domain.usecase.SourcesUseCase

import javax.inject.Inject

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


    fun getSourceTotalResult(): Int? {
        return getSourceViewState().value?.data?.size
    }
}
