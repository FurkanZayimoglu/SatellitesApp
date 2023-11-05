package com.example.satellitesapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SatelliteData(
    val id: Int,
    val active: Boolean,
    val name: String
) : Parcelable
