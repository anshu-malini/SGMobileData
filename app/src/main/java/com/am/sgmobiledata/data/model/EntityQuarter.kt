package com.am.sgmobiledata.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "QuarterEntity")
data class EntityQuarter @Ignore constructor(
    @PrimaryKey(autoGenerate = true) var _autoQuarterId: Int = 0,

    @ColumnInfo(name = "quarterName")
    var quarterName: String? = null,

    @ColumnInfo(name = "volumePerQuarter")
    var volumePerQuarter: Double? = 0.0,

    @ColumnInfo(name = "quarterId")
    var quarterId: Int? = 0
) : Parcelable
{
    constructor() : this(0, "", 0.0, 0)

    override fun toString(): String {
        return "EntityQuarter(_autoQuarterId=$_autoQuarterId, quarterName=$quarterName, volumePerQuarter=$volumePerQuarter, quarterId=$quarterId)"
    }
}