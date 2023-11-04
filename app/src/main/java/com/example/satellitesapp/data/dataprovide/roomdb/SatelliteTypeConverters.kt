package com.example.satellitesapp.data.dataprovide.roomdb

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.satellitesapp.data.model.PositionData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class SatelliteTypeConverters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromString(value: String?): List<PositionData?> {
        val listType = object : TypeToken<ArrayList<PositionData?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<PositionData?>?): String {
        return gson.toJson(list)
    }
}