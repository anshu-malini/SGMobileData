package com.am.sgmobiledata.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Links
    (
    @ColumnInfo(name = "next")
    @SerializedName("next")
    var next: String? = null,

    @ColumnInfo(name = "start")
    @SerializedName("start")
    var start: String? = null
) {
    constructor() : this("", "")

    override fun toString(): String {
        return "Links(next=$next, start=$start)"
    }
}