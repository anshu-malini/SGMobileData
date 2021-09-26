package com.am.sgmobiledata.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "fields_Item")
data class FieldsItem(
    @PrimaryKey(autoGenerate = true)
    var _autoId: Long = 0,

    @ColumnInfo(name = "idFieldsItem")
    @SerializedName("id")
    var id: String? = null,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String? = null
) : Parcelable {
    constructor() : this(0, "", "")

    override fun toString(): String {
        return "FieldsItem(_autoId=$_autoId, id=$id, type=$type)"
    }
}