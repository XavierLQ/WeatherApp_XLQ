package com.assesment_project.weatherapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "locationSearched")
data class LocationSearchedEntity (
    @ColumnInfo(name = "city_name") val cityName: String?,

){
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
    @ColumnInfo(name = "timestamp")
    var timestamp: Long? = 0L
}
