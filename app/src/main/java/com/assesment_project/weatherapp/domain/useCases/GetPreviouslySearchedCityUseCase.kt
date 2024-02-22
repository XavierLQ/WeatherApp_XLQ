package com.assesment_project.weatherapp.domain.useCases

import android.content.Context
import com.assesment_project.weatherapp.data.roomDb.CityDatabase
import javax.inject.Inject

class GetPreviouslySearchedCityUseCase @Inject constructor(private val context: Context, private val cityDB: CityDatabase){

    fun getPreviousSearchedCity(): String?{

        val previouslySearchedCity = cityDB.cityDao().getLatestSearch()

        return null
    }
}