package com.am.sgmobiledata.data.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Mobile_Data_Table")
data class Result(
    @PrimaryKey(autoGenerate = true) var _autoId: Int = 0,

    @SerializedName("total")
    @ColumnInfo(name = "total")
    var total: Int,

    @SerializedName("records")
    var records: MutableList<RecordsItem>? = null,

    @SerializedName("_links")
    @Embedded
    var links: Links? = null,

    @SerializedName("limit")
    @ColumnInfo(name = "limit")
    var limit: Int? = null,

    @SerializedName("resource_id")
    @ColumnInfo(name = "resource_id")
    var resourceId: String? = null,

    @SerializedName("fields")
    var fields: MutableList<FieldsItem?>? = null,

    @SerializedName("_year")
    var years: MutableList<EntityYear>? = null
): Parcelable {
    constructor() : this(0, 0, null, Links(), 0, null, null, null)

    override fun toString(): String {
        return "Result(_autoId=$_autoId, total=$total, records=$records, links=$links, limit=$limit, resourceId=$resourceId, fields=$fields, years=$years)"
    }
}