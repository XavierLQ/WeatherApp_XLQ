package com.assesment_project.weatherapp.data.restApi

import com.assesment_project.weatherapp.data.model.WeatherResult
import retrofit2.Response

class NetworkRepoImpl(private val client: InetworkClient): NetworkRepository {

    private val interceptor = client.getInterceptor()
    private val okHttpClient = client.getOkHttpClient(interceptor)
    private val networkAPI = client.getNetworkService(okHttpClient)
    override suspend fun getWeather(city: String): Response<WeatherResult> = networkAPI.getWeather(city)

}