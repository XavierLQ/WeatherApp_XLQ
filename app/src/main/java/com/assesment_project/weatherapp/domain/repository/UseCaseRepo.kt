package com.assesment_project.weatherapp.domain.repository

import android.location.Location
import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response

interface UseCaseRepo {
    suspend fun getWeather(city: String): Response<WeatherResult>
    suspend fun getLocation(): String?
    suspend fun getPreviouslySearchedCity(): String?
    suspend fun storeCitySearch(city: String)
}