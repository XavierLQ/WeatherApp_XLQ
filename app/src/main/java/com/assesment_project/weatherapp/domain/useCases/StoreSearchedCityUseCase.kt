package com.assesment_project.weatherapp.domain.useCases

import android.content.Context
import com.assesment_project.weatherapp.data.roomDb.CityDatabase
import com.assesment_project.weatherapp.domain.entity.LocationSearchedEntity
import javax.inject.Inject

class StoreSearchedCityUseCase @Inject constructor(private val context: Context, private val cityDB: CityDatabase) {
    suspend fun storeSearchedCity(city: String){
        val searchedLocation = LocationSearchedEntity(city)
        cityDB.cityDao().insertSearch(searchedLocation)
    }
}