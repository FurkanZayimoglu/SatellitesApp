package com.example.satellitesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.satellitesapp.utils.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "satellite_detail")
data class SatelliteDetailData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerialName(Constants.Name.COST_PER_LAUNCH)
    val costPerLaunch: Long,
    @SerialName(Constants.Name.FIRST_FLIGHT)
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
