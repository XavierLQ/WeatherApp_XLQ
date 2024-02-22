package com.assesment_project.weatherapp.domain.useCases

import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.data.restApi.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend fun getWeather(city: String): Response<WeatherResult> = networkRepository.getWeather(city)

}