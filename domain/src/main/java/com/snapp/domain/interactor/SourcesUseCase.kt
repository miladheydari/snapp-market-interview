package com.snapp.domain.interactor

import androidx.lifecycle.LiveData
import com.snapp.domain.state.SourceViewState
import com.snapp.domain.repository.SourceRepository
import com.snapp.domain.utils.UseCaseLiveData
import javax.inject.Inject

class SourcesUseCase @Inject internal constructor(
    private val repository: SourceRepository
) : UseCaseLiveData<SourceViewState, SourcesUseCase.SourceParams>() {

    override fun buildUseCaseObservable(params: SourceParams?): LiveData<SourceViewState> {
        return repository.loadSource(
            fetchRequired = params?.fetchRequired ?: true
        )
    }


    class SourceParams(
        val fetchRequired: Boolean
    ) : Params()
}
