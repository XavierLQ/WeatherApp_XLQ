package com.assesment_project.weatherapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.presentation.viewModel.WeatherViewModel
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeatherDetailScreen(city: String,
                        navController: NavHostController,
                        paddingValues: PaddingValues,
                        vm: WeatherViewModel){

    var weatherResult: WeatherResult? by remember { mutableStateOf(null) }

    weatherResult = vm.weatherLiveData.observeAsState().value
    val temp = weatherResult?.main?.temp
    val pressure = weatherResult?.main?.pressure

    Column(
        Modifier.padding(paddingValues).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val iconId = weatherResult?.weather?.get(0)?.icon
        GlideImage(
            imageModel = "https://openweathermap.org/img/wn/${iconId}@2x.png",
            modifier = Modifier.size(200.dp)
        )

        Text("Temperature $temp")
        Text("Pressure $pressure")
    }
}