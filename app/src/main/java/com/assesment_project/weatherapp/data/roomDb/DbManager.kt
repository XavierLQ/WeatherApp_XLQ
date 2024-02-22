package com.assesment_project.weatherapp.data.roomDb

import android.content.Context
import androidx.room.Room

object DbManager {
    private var instance: CityDatabase? = null
    fun getRoomDbInstance(context: Context): CityDatabase {
        if (instance == null) {
            synchronized(CityDatabase::class) {
                instance = buildRoomDB(context)
            }
        }
        return instance!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            CityDatabase::class.java,
            "locationSearchesDB"
        ).build()
}