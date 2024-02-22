package com.assesment_project.weatherapp.data.location

import android.location.Location
import javax.inject.Inject

class LocationRepoImpl @Inject constructor(private val serviceManager: LocationServiceManager): LocationRepo {

    override fun getLocation(): Location = serviceManager.getDeviceLocation()
}