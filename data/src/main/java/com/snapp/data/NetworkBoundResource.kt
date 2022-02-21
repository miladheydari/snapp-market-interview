package com.snapp.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.snapp.domain.state.BaseViewState
import com.snapp.domain.state.ViewState
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<ResultType, RequestType,E : BaseViewState<ResultType>>
@MainThread internal constructor() {
    private val result = MediatorLiveData<BaseViewState<ResultType>>()
    private var mDisposable: Disposable? = null
    private var dbSource: LiveData<ResultType>

    internal val asLiveData: LiveData<E>
        get() = result.map { it as E }

    init {
        result.value = BaseViewState(ViewState.LOADING)
        @Suppress("LeakingThis")
        dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.setValue(

                        BaseViewState(
                            ViewState.COMPLETE,
                            data = newData
                        )
                    )
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource) { newData -> result.setValue(BaseViewState(ViewState.LOADING)) }
        createCall()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<RequestType> {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        mDisposable = d
                    }
                }

                override fun onSuccess(requestType: RequestType) {
                    result.removeSource(dbSource)
                    saveResultAndReInit(requestType)
                }

                override fun onError(e: Throwable) {
                    result.removeSource(dbSource)
                    result.addSource(dbSource) { newData ->
                        result.setValue(
                            BaseViewState(
                                viewState = ViewState.ERROR,
                                errorMessage = e.message.toString(),
                                throwable = e
                            )
                        )
                    }
                    mDisposable!!.dispose()
                }
            })
    }

    @MainThread
    private fun saveResultAndReInit(response: RequestType) {
        Completable
            .fromCallable { saveCallResult(response) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        mDisposable = d
                    }
                }

                override fun onComplete() {
                    result.addSource(loadFromDb()) { newData ->
                        result.setValue(BaseViewState(ViewState.COMPLETE,newData))
                    }
                    mDisposable!!.dispose()
                }

                override fun onError(e: Throwable) {
                    result.removeSource(dbSource)
                    result.addSource(dbSource) { newData ->
                        result.setValue(BaseViewState(ViewState.ERROR,null,e.message.toString(), e))
                    }
                    mDisposable!!.dispose()
                }
            })
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): Single<RequestType>
}
