package com.am.sgmobiledata.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "records_Item")
data class RecordsItem(
    @PrimaryKey(autoGenerate = true)
    var _autoIdRecordsItem: Long = 0,

    @ColumnInfo(name = "volume_of_mobile_data")
    @SerializedName("volume_of_mobile_data")
    var volumeOfMobileData: String? = null,

    @ColumnInfo(name = "_idRecordsItem")
    @SerializedName("_id")
    var id: Int? = null,

    @ColumnInfo(name = "quarter")
    @field:SerializedName("quarter")
    var quarter: String? = null
) : Parcelable {
    constructor() : this(0, "", 0, "")

    override fun toString(): String {
        return "RecordsItem(_autoIdRecordsItem=$_autoIdRecordsItem, volumeOfMobileData=$volumeOfMobileData, id=$id, quarter=$quarter)"
    }
}