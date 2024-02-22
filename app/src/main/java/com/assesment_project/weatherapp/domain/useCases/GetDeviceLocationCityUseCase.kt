package com.assesment_project.weatherapp.domain.useCases

import android.location.Location
import com.assesment_project.weatherapp.data.location.LocationRepo
import javax.inject.Inject

class GetDeviceLocationCityUseCase @Inject constructor(private val locationRepo: LocationRepo) {
    suspend fun getLocation(): String? {
        val location = locationRepo.getLocation()
        return location?.let{
            convertLocationToCity(it)
        }
    }
    private fun convertLocationToCity(location: Location): String {
        return ""
    }
}