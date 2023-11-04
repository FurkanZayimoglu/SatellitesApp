package com.example.satellitesapp.data.dataprovide.json

import java.io.InputStream

fun interface AssetManager {
    fun open(fileName: String): InputStream
}