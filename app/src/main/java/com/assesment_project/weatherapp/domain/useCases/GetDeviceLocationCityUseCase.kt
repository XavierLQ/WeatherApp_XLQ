package com.assesment_project.weatherapp.domain.useCases

import com.assesment_project.weatherapp.data.location.LocationRepo

class GetDeviceLocationCityUseCase(private val locationRepo: LocationRepo) {
    fun getLocation(): String = locationRepo.getLocation()
}