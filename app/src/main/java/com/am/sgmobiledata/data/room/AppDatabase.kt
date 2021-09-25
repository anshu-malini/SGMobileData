package com.am.sgmobiledata.data.room

import android.content.Context
import androidx.room.*
import com.am.sgmobiledata.data.model.*


@Database(
    entities = [(Result::class), (RecordsItem::class), (FieldsItem::class),
        (EntityYear::class), (EntityQuarter::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mobileDataDao(): MobileDataDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "am_data_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
