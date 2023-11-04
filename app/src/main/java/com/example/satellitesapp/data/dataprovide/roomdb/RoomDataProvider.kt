package com.example.satellitesapp.data.dataprovide.roomdb

import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.model.SatellitePositionData

interface RoomDataProvider {
    suspend fun getSatelliteDetails(): List<SatelliteDetailData>?
    suspend fun getSatellitePositions(): List<SatellitePositionData>?
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailData)
    suspend fun insertSatellitePosition(satellitePosition: SatellitePositionData)
    suspend fun deleteSatelliteDetails(satelliteDetail: SatelliteDetailData)
    suspend fun deleteSatellitePositions(satellitePosition: SatellitePositionData)
}