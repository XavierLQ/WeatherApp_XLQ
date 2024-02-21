package com.assesment_project.weatherapp.data.location

class LocationRepoImpl(private val serviceManager: LocationServiceManager): LocationRepo {

    override fun getLocation():String = serviceManager.getDeviceLocation()
}