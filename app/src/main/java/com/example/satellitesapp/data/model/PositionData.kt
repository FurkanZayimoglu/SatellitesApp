package com.example.satellitesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PositionData(
    val posX: Double,
    val posY: Double
)