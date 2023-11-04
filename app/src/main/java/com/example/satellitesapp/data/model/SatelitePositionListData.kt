package com.example.satellitesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SatelitePositionListData(
    val positionDataList: List<SatellitePositionData>
)