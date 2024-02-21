package com.assesment_project.weatherapp.domain.repository

import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.domain.useCases.GetDeviceLocationCItyUseCase
import com.assesment_project.weatherapp.domain.useCases.GetPreviouslySearchedCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetWeatherByCityUseCase
import retrofit2.Response

class UseCaseRepoImpl(
    val deviceLocationUseCase: GetDeviceLocationCItyUseCase,
    val previousSearchUseCase: GetPreviouslySearchedCityUseCase,
    private val weatherUseCase: GetWeatherByCityUseCase
): UseCaseRepo {
    override suspend fun getWeather(city: String): Response<WeatherResult> = weatherUseCase.getWeather(city)
}