package com.assesment_project.weatherapp.data.location

import android.location.Location

class LocationRepoImpl(private val serviceManager: LocationServiceManager): LocationRepo {

    override fun getLocation(): Location = serviceManager.getDeviceLocation()
}