package com.example.satellitesapp.data.dataprovide.json

import com.example.satellitesapp.data.model.SatelitePositionListData
import com.example.satellitesapp.data.model.SatelliteData
import com.example.satellitesapp.data.model.SatelliteDetailData
import com.example.satellitesapp.data.model.SatellitePositionData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.Json
import java.io.InputStream

@OptIn(ExperimentalSerializationApi::class)
class JsonDataProvideImpl (
    private val ioDispatcher: CoroutineDispatcher,
    private val assetManager: AssetManager,
    private val json: Json
) : JsonDataProvide {

    override suspend fun getSatellites(): List<SatelliteData> = withContext(ioDispatcher) {
        assetManager.open(SATELLITE_LIST_JSON).use(json::decodeFromStream)
    }

    override suspend fun searchSatellites(query: String): List<SatelliteData> =
        withContext(ioDispatcher) {
            assetManager.open(SATELLITE_LIST_JSON)
                .use<InputStream, List<SatelliteData>>(json::decodeFromStream)
                .filter {
                    it.name.lowercase().contains(query.lowercase())
                }
        }

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailData? =
        withContext(ioDispatcher) {
            assetManager.open(SATELLITE_DETAIL_JSON)
                .use<InputStream, Array<SatelliteDetailData>>(json::decodeFromStream)
                .find {
                    it.id == satelliteId
                }
        }

    override suspend fun getSatellitePositions(satelliteId: Int): SatellitePositionData? =
        withContext(ioDispatcher) {
            assetManager.open(SATELLITE_POSITIONS_JSON)
                .use<InputStream, SatelitePositionListData>(json::decodeFromStream).positionDataList
                .find {
                    it.id == satelliteId
                }
        }

    companion object {
        private const val SATELLITE_LIST_JSON = "satellite-list.json"
        private const val SATELLITE_DETAIL_JSON = "satellite-detail.json"
        private const val SATELLITE_POSITIONS_JSON = "positions.json"
    }
}