package com.assesment_project.weatherapp.data.repository

import retrofit2.Response

interface NetworkRepository {
    suspend fun getWeather(city: String): Response<String>
}