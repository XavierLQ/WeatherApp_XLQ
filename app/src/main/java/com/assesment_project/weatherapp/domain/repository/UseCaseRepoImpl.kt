package com.assesment_project.weatherapp.domain.repository

import android.location.Location
import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.domain.useCases.GetDeviceLocationCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetPreviouslySearchedCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetWeatherByCityUseCase
import retrofit2.Response

class UseCaseRepoImpl(
    private val deviceLocationUseCase: GetDeviceLocationCityUseCase,
    private val previousSearchUseCase: GetPreviouslySearchedCityUseCase,
    private val weatherUseCase: GetWeatherByCityUseCase
): UseCaseRepo {
    override suspend fun getWeather(city: String): Response<WeatherResult> =
        weatherUseCase.getWeather(city)
    override suspend fun getLocation(): Location =
        deviceLocationUseCase.getLocation()
    override suspend fun getPreviouslySearchedCity(): String? =
        previousSearchUseCase.getPreviousSearchedCity()
}