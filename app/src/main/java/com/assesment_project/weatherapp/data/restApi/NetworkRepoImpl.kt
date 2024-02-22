package com.assesment_project.weatherapp.data.restApi

import android.location.Location
import com.assesment_project.weatherapp.data.model.GeocodingResult
import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response
import javax.inject.Inject

class NetworkRepoImpl @Inject constructor(private val networkAPI: RestAPI): NetworkRepository {

    override suspend fun getWeather(city: String): Response<WeatherResult> =
        networkAPI.getWeather(city)
    override suspend fun getCityFromLocation(lat: Double, lon:Double): Response<GeocodingResult> =
        networkAPI.getCityFromGeocoding(lat, lon)

}