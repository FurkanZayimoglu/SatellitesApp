package com.example.satellitesapp.data.dataprovide.json

import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.model.SatellitePositionData

interface JsonDataProvide {
    suspend fun getSatellites(): List<SatelliteData>?
    suspend fun searchSatellites(query: String): List<SatelliteData>
    suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailData?
    suspend fun getSatellitePositions(satelliteId: Int): SatellitePositionData?
}