package com.am.sgmobiledata.data.api

class DataUsageQueryBuilder : QueryBuilder() {
    private var limit: String? = null

    fun setLimit(limit: String?): DataUsageQueryBuilder {
        this.limit = limit
        return this
    }

    override fun putQueryParams(queryParams: MutableMap<String, String>) {
        limit?.apply { queryParams["limit"] = this }
    }
}