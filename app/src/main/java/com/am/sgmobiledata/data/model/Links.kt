package com.am.sgmobiledata.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @ColumnInfo(name = "next")
    @SerializedName("next")
    var next: String? = null,

    @ColumnInfo(name = "start")
    @SerializedName("start")
    var start: String? = null
) : Parcelable {
    constructor() : this("", "")

    override fun toString(): String {
        return "Links(next=$next, start=$start)"
    }
}