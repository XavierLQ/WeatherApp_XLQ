package com.assesment_project.weatherapp.data.restApi

import com.assesment_project.weatherapp.data.model.GeocodingResult
import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAPI {

    @GET(PATH_URL_WEATHER)
    suspend fun getWeather(
        @Query("q") city:String,
        @Query("appid") apiKey: String = API_KEY
        ): Response<WeatherResult>

    @GET(PATH_URL_GEOCODING)
    suspend fun getCityFromGeocoding(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY
    ): Response<GeocodingResult>

    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val PATH_URL_WEATHER = "/data/2.5/weather"
        const val PATH_URL_GEOCODING = "/geo/1.0/reverse"

        /**
         * Bad practice to leave [API_KEY] on the repo instead of local env, but left it here so it
         * can be tested for the assessment.
         */

        const val API_KEY = "09b3c6bfb77b7827c9460bdb78106308"
    }
}