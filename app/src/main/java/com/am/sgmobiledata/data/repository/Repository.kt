package com.am.sgmobiledata.data.repository

import com.am.sgmobiledata.data.remote.RemoteDataSource
import com.am.sgmobiledata.data.room.MobileDataDao
import com.am.sgmobiledata.utils.performGetOperation
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: MobileDataDao
)  {
//    fun getData(id: Int) = performGetOperation(
//        databaseQuery = { localDataSource.getRecord(id) },
//        networkCall = { remoteDataSource.getAllDataUsage() },
//        saveCallResult = {
//            localDataSource.insert(it.result?.records?.get(id))
//        }
//    )

    fun getAllData() = performGetOperation(
        databaseQuery = { localDataSource.getAllRecords() },
        networkCall = { remoteDataSource.getAllDataUsage() },
        saveCallResult = {
            localDataSource.insert(it.result)
        }
    )
}
