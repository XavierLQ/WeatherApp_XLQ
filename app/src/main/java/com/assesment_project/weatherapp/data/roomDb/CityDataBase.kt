package com.assesment_project.weatherapp.data.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assesment_project.weatherapp.domain.entity.LocationSearchedEntity

@Database(entities = [LocationSearchedEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

}
