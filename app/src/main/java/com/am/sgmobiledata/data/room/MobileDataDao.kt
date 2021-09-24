package com.am.sgmobiledata.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.am.sgmobiledata.data.model.RecordsItem
import com.am.sgmobiledata.data.model.Result

@Dao
interface MobileDataDao {
    @Query("SELECT * FROM Mobile_Data_Table")
    fun getAllRecords(): LiveData<Result>

//    @Query("SELECT * FROM Mobile_Data_Table where _idRecordsItem=:id")
//    fun getRecord(id: Int): LiveData<RecordsItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(items: Result?)

    @Query("DELETE FROM Mobile_Data_Table")
    fun deleteAll()

    @Update
    fun update(items: Result)
}
