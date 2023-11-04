package com.example.satellitesapp.data.dataprovide.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.model.SatellitePositionData

@Dao
interface SatellitesDao {
    @Query("SELECT * FROM satellite_detail")
    suspend fun getSatelliteDetails(): List<SatelliteDetailData>

    @Query("SELECT * FROM satellite_positions")
    suspend fun getSatellitePositions(): List<SatellitePositionData>

    @Insert
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailData)

    @Insert
    suspend fun insertSatellitePosition(satellitePosition: SatellitePositionData)

    @Delete
    suspend fun deleteSatelliteDetails(satelliteDetail: SatelliteDetailData)

    @Delete
    suspend fun deleteSatellitePositions(satellitePosition: SatellitePositionData)
}