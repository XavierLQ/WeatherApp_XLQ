package com.assesment_project.weatherapp.data.restApi

import android.location.Location
import com.assesment_project.weatherapp.data.model.GeocodingResult
import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response

interface NetworkRepository {
    suspend fun getWeather(city: String): Response<WeatherResult>
    suspend fun getCityFromLocation(lat: Double, lon: Double): Response<GeocodingResult>
}