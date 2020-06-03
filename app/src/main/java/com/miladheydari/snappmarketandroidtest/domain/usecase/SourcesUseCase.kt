package com.miladheydari.snappmarketandroidtest.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.miladheydari.snappmarketandroidtest.domain.model.Source
import com.miladheydari.snappmarketandroidtest.repo.SourcesRepository

import com.miladheydari.snappmarketandroidtest.ui.source.SourceViewState
import com.miladheydari.snappmarketandroidtest.utils.UseCaseLiveData
import com.miladheydari.snappmarketandroidtest.utils.domain.Resource
import javax.inject.Inject

class SourcesUseCase @Inject internal constructor(
    private val repository: SourcesRepository
) : UseCaseLiveData<SourceViewState, SourcesUseCase.SourceParams,
        SourcesRepository>() {

    override fun getRepository():SourcesRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: SourceParams?): LiveData<SourceViewState> {
        return repository.loadSource(
            fetchRequired = params?.fetchRequired ?: true
        ).map {
            onCurrentWeatherResultReady(it)
        }
    }

    private fun onCurrentWeatherResultReady(resource: Resource<List<Source>>): SourceViewState {
        return SourceViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SourceParams(
        val fetchRequired: Boolean
    ) : Params()
}
