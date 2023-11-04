package com.example.satellitesapp.data.dataprovide.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.model.SatellitePositionData

@Database(
    entities = [SatelliteDetailData::class, SatellitePositionData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SatelliteTypeConverters::class)
abstract class SatellitesDatabase : RoomDatabase() {
    abstract fun getSatelliteDao(): SatellitesDao
}