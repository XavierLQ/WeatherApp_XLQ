package com.assesment_project.weatherapp.domain.useCases

import com.assesment_project.weatherapp.data.repository.NetworkRepository
import retrofit2.Response

class GetWeatherByCityUseCase(private val networkRepository: NetworkRepository) {

    suspend fun getWeather(city: String): Response<String> = networkRepository.getWeather(city)

}