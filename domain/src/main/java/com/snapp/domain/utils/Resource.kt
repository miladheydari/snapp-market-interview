package com.snapp.domain.utils

sealed class Resource<out D>{

    object Loading:Resource<Nothing>()
    data class Success<out D>(val data: D?):Resource<D>()
    data class Error<out D>(val massage:String,val data:D?,val throwable: Throwable?):Resource<D>()

}