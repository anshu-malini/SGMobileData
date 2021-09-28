package com.am.sgmobiledata.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.am.sgmobiledata.data.model.EntityQuarter
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.data.model.Result

@Dao
interface MobileDataDao {
    @Query("SELECT * FROM Mobile_Data_Table")
    fun getAllRecords(): LiveData<Result>

    @Query("SELECT * FROM Year_Entity")
    fun getAllYearLogs(): MutableList<EntityYear>

    @Query("SELECT * FROM Year_Entity where _yearId=:yearId")
    fun getYearLog(yearId: Int): EntityYear

    @Query("SELECT * FROM Quarter_Entity where rowId=:qId")
    fun getQuarter(qId: Long): EntityQuarter

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(items: Result?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertYear(item: EntityYear)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuarter(item: EntityQuarter): Long

    @Update
    fun updateYear(year: EntityYear)
}
