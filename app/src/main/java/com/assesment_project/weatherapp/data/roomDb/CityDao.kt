package com.assesment_project.weatherapp.data.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assesment_project.weatherapp.domain.entity.LocationSearchedEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM locationSearched")
    fun getAll(): List<LocationSearchedEntity>

    @Query("SELECT * FROM locationSearched ORDER BY timestamp DESC LIMIT 1")
    fun getLatestSearch(): LocationSearchedEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: LocationSearchedEntity)

    fun insertSearch(city: LocationSearchedEntity){
        insert(city.apply{
            timestamp = System.currentTimeMillis()
        })
    }

    @Delete
    fun delete(city: LocationSearchedEntity)
}
