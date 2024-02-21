package com.assesment_project.weatherapp.data.restApi

import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAPI {

    @GET("$PATH_URL{city}")
    suspend fun getWeather(@Path("city") city:String): Response<WeatherResult>

    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val PATH_URL = "weather?q="

        /**
         * Bad practice to leave [API_KEY] on the repo instead of local env, but left it here so it
         * can be tested for the assessment.
         */

        const val API_KEY = "09b3c6bfb77b7827c9460bdb78106308"
    }
}