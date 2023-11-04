package com.example.satellitesapp.di

import com.example.satellitesapp.data.dataprovide.json.JsonDataProvide
import com.example.satellitesapp.data.dataprovide.roomdb.RoomDataProvider
import com.example.satellitesapp.data.repository.SatelliteRepository
import com.example.satellitesapp.data.repository.SatelliteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSatelliteRepository(
        jsonDataSource: JsonDataProvide,
        roomDataSource: RoomDataProvider
    ): SatelliteRepository = SatelliteRepositoryImpl(jsonDataSource, roomDataSource)
}