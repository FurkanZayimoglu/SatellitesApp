package com.example.satellitesapp.di

import android.content.Context
import androidx.room.Room
import com.example.satellitesapp.data.dataprovide.json.AssetManager
import com.example.satellitesapp.data.dataprovide.json.JsonDataProvide
import com.example.satellitesapp.data.dataprovide.json.JsonDataProvideImpl
import com.example.satellitesapp.data.dataprovide.roomdb.RoomDataProvider
import com.example.satellitesapp.data.dataprovide.roomdb.RoomDataProviderImpl
import com.example.satellitesapp.data.dataprovide.roomdb.SatelliteTypeConverters
import com.example.satellitesapp.data.dataprovide.roomdb.SatellitesDao
import com.example.satellitesapp.data.dataprovide.roomdb.SatellitesDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesFakeAssetManager(
        @ApplicationContext context: Context,
    ): AssetManager = AssetManager(context.assets::open)

    @Provides
    @Singleton
    fun provideJsonDataSource(
        ioDispatcher: CoroutineDispatcher,
        assetManager: AssetManager,
        json: Json
    ): JsonDataProvide = JsonDataProvideImpl(ioDispatcher, assetManager, json)

    @Provides
    @Singleton
    fun provideRoomDataSource(
        satellitesDao: SatellitesDao,
        ioDispatcher: CoroutineDispatcher
    ): RoomDataProvider =
        RoomDataProviderImpl(satellitesDao, ioDispatcher)

    @Provides
    @Singleton
    fun provideSatellitesDatabase(
        @ApplicationContext context: Context,
        typeConverters: SatelliteTypeConverters
    ): SatellitesDatabase = Room
        .databaseBuilder(context, SatellitesDatabase::class.java, "satellites.db")
        .addTypeConverter(typeConverters)
        .build()

    @Provides
    @Singleton
    fun provideSatelliteDao(satellitesDatabase: SatellitesDatabase): SatellitesDao =
        satellitesDatabase.getSatelliteDao()
}