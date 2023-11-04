package com.example.satellitesapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SatelliteData(
    val id: Int,
    val active: Boolean,
    val name: String
)
