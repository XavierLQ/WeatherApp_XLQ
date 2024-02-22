package com.assesment_project.weatherapp.di

import android.content.Context
import com.assesment_project.weatherapp.data.roomDb.CityDatabase
import com.assesment_project.weatherapp.data.roomDb.DbManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CityDatabase =
        DbManager.getRoomDbInstance(context)
}