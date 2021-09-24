package com.am.sgmobiledata.data.remote

import com.am.sgmobiledata.data.model.MobileDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MobileDataService {
    @GET("action/datastore_search")
    suspend fun getDataUsage(@QueryMap query: Map<String, String>) : Response<MobileDataResponse>
}