package com.assesment_project.weatherapp.domain.repository

import android.location.Location
import com.assesment_project.weatherapp.data.model.GeocodingResult
import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.domain.useCases.GetDeviceLocationCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetPreviouslySearchedCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetWeatherByCityUseCase
import com.assesment_project.weatherapp.domain.useCases.StoreSearchedCityUseCase
import retrofit2.Response
import javax.inject.Inject

class UseCaseRepoImpl @Inject constructor(
    private val deviceLocationUseCase: GetDeviceLocationCityUseCase,
    private val previousSearchUseCase: GetPreviouslySearchedCityUseCase,
    private val weatherUseCase: GetWeatherByCityUseCase,
    private val storeSearchedCityUseCase: StoreSearchedCityUseCase
): UseCaseRepo {
    override suspend fun getWeather(city: String): Response<WeatherResult> =
        weatherUseCase.getWeather(city)
    override suspend fun getLocation(): Response<GeocodingResult>? =
        deviceLocationUseCase.getLocation()
    override suspend fun getPreviouslySearchedCity(): String? =
        previousSearchUseCase.getPreviousSearchedCity()

    override suspend fun storeCitySearch(city: String) {
        storeSearchedCityUseCase.storeSearchedCity(city)
    }
}