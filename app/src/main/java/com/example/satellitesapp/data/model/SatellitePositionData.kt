package com.example.satellitesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "satellite_positions")
data class SatellitePositionData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val positions: List<PositionData>
)