package com.assesment_project.weatherapp.di

import android.content.Context
import com.assesment_project.weatherapp.data.location.LocationRepo
import com.assesment_project.weatherapp.data.location.LocationRepoImpl
import com.assesment_project.weatherapp.data.location.LocationServiceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideLocationManager(@ApplicationContext context: Context): LocationServiceManager =
        LocationServiceManager(context)

    @Provides
    fun provideLocationRepo(serviceManager: LocationServiceManager): LocationRepo =
        LocationRepoImpl(serviceManager)
}