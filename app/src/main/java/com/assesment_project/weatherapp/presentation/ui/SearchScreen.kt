package com.assesment_project.weatherapp.presentation.ui

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.assesment_project.weatherapp.presentation.viewModel.WeatherViewModel


@Composable
fun SearchView(paddingValues: PaddingValues, navController: NavHostController, vm: WeatherViewModel){

    var text by rememberSaveable { mutableStateOf("") }


    Column(Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ,
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("City") }
        )
        Button(onClick = { weatherScreen(text, navController, vm) },) {
            Text(text = "Search City")
        }
    }
}

@Composable
fun SearchScreen(vm: WeatherViewModel,
                 navController: NavHostController,
                 paddingValues: PaddingValues,
                 context: Context){

    var hasLocationPermission by rememberSaveable{ mutableStateOf(false) }
    var redirected by rememberSaveable { mutableStateOf(false) }
    hasLocationPermission = hasLocationPermission(context)

    if(hasLocationPermission && !redirected){
        vm.getCurrentLocation()
        val currentCityResult = vm.currentLocationLiveData.observeAsState().value
        val city = currentCityResult?.get(0)?.localNames?.en
        city?.let{
            redirected = true
            navController.navigate("weatherSearch/$it")
        }
    } else{

        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted: Boolean ->
                    hasLocationPermission = isGranted
                    vm.getCurrentLocation()
                }
            )
        SideEffect {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    // Handle the logic at which auto connects to either previous searched city or device location.
    //With enough time, I would add a toast to notify the user of network calls or device location
    //failures.

    //TODO: I would add a Sealed Class to handle the response state, and handle the toast on UI layer.

    if(!hasLocationPermission && !redirected){
        vm.getLatestSearch()
        val latestSearch = vm.latestSearchLiveData.observeAsState().value
        if(latestSearch != null){
            redirected = true
            weatherScreen(latestSearch, navController, vm)
        } else{
            SearchView(paddingValues, navController, vm)
        }
    } else if(hasLocationPermission && !redirected){
        vm.getCurrentLocation()
        val currentCityResult = vm.currentLocationLiveData.observeAsState().value
        val city = currentCityResult?.get(0)?.localNames?.en
        city?.let{
            redirected = true
            navController.navigate("weatherSearch/$it")
        } ?: SearchView(paddingValues, navController, vm)
    } else{
        SearchView(paddingValues, navController, vm)
    }
}

private fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

private fun weatherScreen(city: String, navController: NavHostController, vm: WeatherViewModel){
    vm.getWeather(city)
    navController.navigate("weatherSearch/$city")
}

