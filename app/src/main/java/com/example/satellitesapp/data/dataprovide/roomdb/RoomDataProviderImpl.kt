package com.example.satellitesapp.data.dataprovide.roomdb

import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.model.SatellitePositionData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RoomDataProviderImpl (
    private val satellitesDao: SatellitesDao,
    private val ioDispatcher: CoroutineDispatcher
) : RoomDataProvider {

    override suspend fun getSatelliteDetails() = withContext(ioDispatcher) {
        satellitesDao.getSatelliteDetails()
    }

    override suspend fun getSatellitePositions() = withContext(ioDispatcher) {
        satellitesDao.getSatellitePositions()
    }

    override suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetailData) =
        withContext(ioDispatcher) {
            satellitesDao.insertSatelliteDetail(satelliteDetail)
        }

    override suspend fun insertSatellitePosition(satellitePosition: SatellitePositionData) =
        withContext(ioDispatcher) {
            satellitesDao.insertSatellitePosition(satellitePosition)
        }

    override suspend fun deleteSatelliteDetails(satelliteDetail: SatelliteDetailData) =
        withContext(ioDispatcher) {
            satellitesDao.deleteSatelliteDetails(satelliteDetail)
        }

    override suspend fun deleteSatellitePositions(satellitePosition: SatellitePositionData) =
        withContext(ioDispatcher) {
            satellitesDao.deleteSatellitePositions(satellitePosition)
        }
}