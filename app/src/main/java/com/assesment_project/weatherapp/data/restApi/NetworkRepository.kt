package com.assesment_project.weatherapp.data.restApi

import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response

interface NetworkRepository {
    suspend fun getWeather(city: String): Response<WeatherResult>
}