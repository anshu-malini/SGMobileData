package com.am.sgmobiledata.data.repository

import com.am.sgmobiledata.data.model.EntityQuarter
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.data.model.RecordsItem
import com.am.sgmobiledata.data.remote.RemoteDataSource
import com.am.sgmobiledata.data.room.MobileDataDao
import com.am.sgmobiledata.utils.performGetOperation
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: MobileDataDao
) {
    fun getData(yearId: Int) = performGetOperation(
        databaseQuery = { localDataSource.getYearLogs(yearId) },
        networkCall = { remoteDataSource.getAllDataUsage() },
        saveCallResult = {
//            localDataSource.insert()
        }
    )

    fun getAllData() = performGetOperation(
        databaseQuery = { localDataSource.getAllRecords() },
        networkCall = { remoteDataSource.getAllDataUsage() },
        saveCallResult = {
            filterYearData(localDataSource, it.result?.records)
            localDataSource.insert(it.result.apply {
                it.result?.years = localDataSource.getAllYearLogs()
            })
        }
    )

    private fun filterYearData(dao: MobileDataDao, records: MutableList<RecordsItem>?) {
        if (records != null) {
            for (record in records) {
                val quarArr = record.quarter?.split("-")
                val yearLogs = dao.getAllYearLogs()
                if (yearLogs.isNullOrEmpty()) {
                    val firstYearEntry = EntityYear()
                    firstYearEntry.yearName = quarArr?.get(0)
                    firstYearEntry.volumePerYear = record.volumeOfMobileData?.toDouble()

                    val newQuarter = EntityQuarter()
                    newQuarter.quarterName = quarArr?.get(1)
                    newQuarter.volumePerQuarter =
                        record.volumeOfMobileData?.toDouble()
                    newQuarter.quarterId = record.id
                    dao.insertQuarter(newQuarter)

                    firstYearEntry.quarter = mutableListOf(EntityQuarter())
                    firstYearEntry.quarter?.add(newQuarter)
                    dao.insertYear(firstYearEntry)

                } else {
                    val alreadyExist =
                        yearLogs.any { x -> x.yearName?.equals(quarArr?.get(0))!! }
                    if (alreadyExist) {
                        yearLogs.forEach { entityYear ->
                            if (entityYear.yearName.equals(quarArr?.get(0))) {
                                entityYear.also { entityObj ->
                                    entityObj.volumePerYear =
                                        (record.volumeOfMobileData)?.toDouble()?.let { it ->
                                            entityYear.volumePerYear?.plus(
                                                it
                                            )
                                        }
                                    val newQuarter = EntityQuarter()
                                    newQuarter.quarterName = quarArr?.get(1)
                                    newQuarter.volumePerQuarter =
                                        record.volumeOfMobileData?.toDouble()
                                    newQuarter.quarterId = record.id
                                    dao.insertQuarter(newQuarter)

                                    entityObj.quarter?.also {
                                        it.add(newQuarter)
                                    }
                                }
                                dao.updateYear(entityYear)
                            }
                        }
                    } else {
                        val newYear = EntityYear()
                        newYear.yearName = quarArr?.get(0)
                        newYear.volumePerYear = record.volumeOfMobileData?.toDouble()
                        val newYearQuarter = EntityQuarter()
                        newYearQuarter.quarterName = quarArr?.get(1)
                        newYearQuarter.volumePerQuarter =
                            record.volumeOfMobileData?.toDouble()
                        newYearQuarter.quarterId = record.id
                        dao.insertQuarter(newYearQuarter)

                        newYear.quarter = mutableListOf(EntityQuarter())
                        newYear.quarter?.add(newYearQuarter)
                        dao.insertYear(newYear)
                    }
                }
            }
        }
    }
}
