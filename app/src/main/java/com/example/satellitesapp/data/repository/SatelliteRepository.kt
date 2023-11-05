package com.example.satellitesapp.data.repository

import com.example.satellitesapp.data.model.PositionData
import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.utils.Resource

interface SatelliteRepository {
    suspend fun getSatellites(): Resource<List<SatelliteData>?>
    suspend fun searchSatellites(query: String): Resource<List<SatelliteData>?>
    suspend fun getSatelliteDetail(satelliteId: Int): Resource<SatelliteDetailData?>
    suspend fun getSatellitePosition(satelliteId: Int): Resource<List<PositionData?>>

}