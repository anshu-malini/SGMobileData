package com.am.sgmobiledata.data.remote

import com.am.sgmobiledata.data.api.DataUsageQueryBuilder
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val dataService: MobileDataService
) : BaseDataSource() {
    suspend fun getAllDataUsage() = getResult {
        dataService.getDataUsage(DataUsageQueryBuilder().build())
    }
}