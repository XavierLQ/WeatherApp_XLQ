package com.assesment_project.weatherapp.data.location

import android.location.Location

interface LocationRepo {
    fun getLocation(): Location
}