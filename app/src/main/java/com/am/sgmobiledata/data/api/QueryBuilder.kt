package com.am.sgmobiledata.data.api

abstract class QueryBuilder {
    private val baseQueryParams by lazy {
        mapOf("resource_id" to "a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
    }

    fun build(): Map<String, String> {
        val queryParams = HashMap(baseQueryParams)
        putQueryParams(queryParams)
        return queryParams
    }

    abstract fun putQueryParams(queryParams: MutableMap<String, String>)

}