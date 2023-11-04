package com.example.satellitesapp.data.repository

import com.example.satellitesapp.data.dataprovide.json.JsonDataProvide
import com.example.satellitesapp.data.dataprovide.roomdb.RoomDataProvider
import com.example.satellitesapp.data.model.PositionData
import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.utils.Resource
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val jsonDataProvide: JsonDataProvide,
    private val roomDataProvide: RoomDataProvider
): SatelliteRepository {
    override suspend fun getSatellites(): Resource<List<SatelliteData>?> {
        return try {
            val satellites = jsonDataProvide.getSatellites()
            Resource.Success(satellites)
        } catch (e: Exception) {
            Resource.Error(e.message.orEmpty())
        }
    }

    override suspend fun searchSatellites(query: String): Resource<List<SatelliteData>?> {
        return try {
            val satellites = jsonDataProvide.getSatellites()
            Resource.Success(satellites)
        } catch (e: Exception) {
            Resource.Error(e.message.orEmpty())
        }
    }

    override suspend fun getSatelliteDetail(satelliteId: Int): Resource<SatelliteDetailData?> {
        val satelliteDetailFromRoom = roomDataProvide.getSatelliteDetails()?.find { it.id == satelliteId }

        return satelliteDetailFromRoom?.let {
            Resource.Success(it)
        } ?: run {
            val satelliteDetailFromJson = jsonDataProvide.getSatelliteDetail(satelliteId)
            return if (satelliteDetailFromJson != null) {
                roomDataProvide.deleteSatelliteDetails(satelliteDetailFromJson)
                roomDataProvide.insertSatelliteDetail(satelliteDetailFromJson)
                Resource.Success(satelliteDetailFromJson)
            } else {
                Resource.Error("Satellite detail not found")
            }
        }

    }

    override suspend fun getSatellitePosition(satelliteId: Int): Resource<PositionData?> {
        val positionList = roomDataProvide.getSatellitePositions()?.find { it.id == satelliteId }?.positions
            ?: jsonDataProvide.getSatellitePositions(satelliteId)?.let {
                roomDataProvide.deleteSatellitePositions(it)
                roomDataProvide.insertSatellitePosition(it)
                it.positions
            }

        // bu fonksiyona bakılacak.

        return positionList?.firstOrNull()?.let {
            Resource.Success(it)
        } ?: Resource.Error("Position not found")
    }
}