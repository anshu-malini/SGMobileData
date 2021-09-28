package com.am.sgmobiledata.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> NetworkResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<NetworkResult<T>> =
    liveData(Dispatchers.IO) {
        emit(NetworkResult.loading())
        val source = databaseQuery.invoke().map { NetworkResult.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == NetworkResult.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == NetworkResult.Status.ERROR) {
            emit(NetworkResult.error(responseStatus.message!!))
            emitSource(source)
        }
    }

suspend fun <T> performSingleOperation(
    databaseQuery: suspend () -> T
): T {
    return withContext(Dispatchers.IO) {
        databaseQuery.invoke()
    }
}