package com.am.sgmobiledata.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Year_Entity")
data class EntityYear @Ignore constructor(
    @PrimaryKey(autoGenerate = true) var _yearId: Int = 0,

    @ColumnInfo(name = "yearName")
    var yearName: String? = null,

    @ColumnInfo(name = "volumePerYear")
    var volumePerYear: Double? = 0.0,

    @SerializedName("quarter")
    var quarter: MutableList<EntityQuarter?>? = null
): Parcelable {
    constructor() : this(0, "", 0.0, null)

    override fun toString(): String {
        return "EntityYear(_yearId=$_yearId, yearName=$yearName, volumePerYear=$volumePerYear, quarter=$quarter)"
    }
}


