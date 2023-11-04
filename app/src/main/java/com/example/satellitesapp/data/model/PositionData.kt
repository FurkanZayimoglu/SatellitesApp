package com.example.satellitesapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PositionData(
    val posX: Double,
    val posY: Double
)