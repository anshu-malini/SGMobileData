package com.am.sgmobiledata.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class MobileDataResponse(
	@PrimaryKey(autoGenerate = true) val _autoId: Int = 0,

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("help")
	val help: String? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)