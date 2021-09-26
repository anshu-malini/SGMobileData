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
    fun getYearLogs(yearId: Int): LiveData<EntityYear>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(items: Result?)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllYear(items: MutableList<EntityYear>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertYear(item: EntityYear)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuarter(item: EntityQuarter)

    @Query("DELETE FROM Mobile_Data_Table")
    fun deleteAll()

    @Update
    fun update(items: Result)

    @Update
    fun updateYear(year: EntityYear)
}
