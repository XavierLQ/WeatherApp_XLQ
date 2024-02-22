package com.assesment_project.weatherapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.assesment_project.weatherapp.presentation.viewModel.WeatherViewModel

@Composable
fun WeatherDetailScreen(city: String,
                        navController: NavHostController,
                        paddingValues: PaddingValues,
                        vm: WeatherViewModel){
    vm.getWeather(city)
    val weatherResult = vm.weatherLiveData.observeAsState().value
    val temp = weatherResult?.main?.temp
    val pressure = weatherResult?.main?.pressure

    Column(Modifier.padding(paddingValues)) {
        Text("Temperature $temp")
        Text("Pressure $pressure")
    }
}