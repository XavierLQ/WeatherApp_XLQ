package com.assesment_project.weatherapp.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LocationServiceManager @Inject constructor(private val context: Context) {
    private val locationClient = LocationServices.getFusedLocationProviderClient(context)
    @SuppressLint("MissingPermission")
    fun getDeviceLocation(): Location{
        //Await for the location to be updated on the result
        if (hasLocationPermissions()) {
            return locationClient.lastLocation.addOnCompleteListener {
                return@addOnCompleteListener
            }.result
        } else{
            throw Exception("Does not have the required permissions")
        }
    }

    private fun hasLocationPermissions(): Boolean{

        val coarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION
        val permissionGranted = PackageManager.PERMISSION_GRANTED
        return (ActivityCompat.checkSelfPermission(context, coarseLocation) == permissionGranted)
    }
}