package com.assesment_project.weatherapp.domain.useCases

import android.location.Location
import com.assesment_project.weatherapp.data.location.LocationRepo
import com.assesment_project.weatherapp.data.model.GeocodingResult
import com.assesment_project.weatherapp.data.restApi.NetworkRepository
import retrofit2.Response
import javax.inject.Inject

class GetDeviceLocationCityUseCase @Inject constructor(private val locationRepo: LocationRepo,
                                                       private val networkRepo: NetworkRepository) {
    suspend fun getLocation(): Response<GeocodingResult>? {
        var location: Location? = null
        location = locationRepo.getLocation()
        return location?.let{
            convertLocationToCity(it)
        }
    }
    private suspend fun convertLocationToCity(location: Location): Response<GeocodingResult> {
        val lat = location.latitude
        val lon = location.longitude
        return networkRepo.getCityFromLocation(lat, lon)
    }
}