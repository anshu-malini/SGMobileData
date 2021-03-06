package com.am.sgmobiledata.data.room

import androidx.room.TypeConverter
import com.am.sgmobiledata.data.model.EntityQuarter
import com.am.sgmobiledata.data.model.EntityYear
import com.am.sgmobiledata.data.model.FieldsItem
import com.am.sgmobiledata.data.model.RecordsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun recordConvertToString(list: MutableList<RecordsItem>?): String? {
        return if (list.isNullOrEmpty()) null
        else Gson().toJson(list)
    }

    @TypeConverter
    fun recordConvertFromString(value: String): MutableList<RecordsItem>? {
        val listType = object : TypeToken<MutableList<RecordsItem>>() {}.type
        return if (value.isNullOrEmpty()) null
        else Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fieldsConvertToString(list: MutableList<FieldsItem>?): String? {
        return if (list.isNullOrEmpty()) null
        else Gson().toJson(list)
    }

    @TypeConverter
    fun fieldsConvertFromString(value: String): MutableList<FieldsItem>? {
        val listType = object : TypeToken<MutableList<FieldsItem>>() {}.type
        return if (value.isNullOrEmpty()) null
        else Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun yearConvertToString(list: MutableList<EntityYear>?): String? {
        return if (list.isNullOrEmpty()) null
        else Gson().toJson(list)
    }

    @TypeConverter
    fun yearConvertFromString(value: String): MutableList<EntityYear>? {
        val listType = object : TypeToken<MutableList<EntityYear>>() {}.type
        return if (value.isNullOrEmpty()) null
        else Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun quarterConvertToString(list: MutableList<EntityQuarter>?): String? {
        return if (list.isNullOrEmpty()) null
        else Gson().toJson(list)
    }

    @TypeConverter
    fun quarterConvertFromString(value: String): MutableList<EntityQuarter>? {
        val listType = object : TypeToken<MutableList<EntityQuarter>>() {}.type
        return if (value.isNullOrEmpty()) null
        else Gson().fromJson(value, listType)
    }

}