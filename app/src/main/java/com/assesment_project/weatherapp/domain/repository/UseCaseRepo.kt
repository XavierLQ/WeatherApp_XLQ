package com.assesment_project.weatherapp.domain.repository

import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response

interface UseCaseRepo {
    suspend fun getWeather(city: String): Response<WeatherResult>
}