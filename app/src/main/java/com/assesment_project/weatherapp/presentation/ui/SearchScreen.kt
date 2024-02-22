package com.assesment_project.weatherapp.presentation.ui

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.assesment_project.weatherapp.presentation.viewModel.WeatherViewModel


@Composable
fun SearchView(paddingValues: PaddingValues, navController: NavHostController){

    var text by rememberSaveable { mutableStateOf("Text") }

    Column(Modifier.padding(paddingValues)){
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )
        Button(onClick = {navController.navigate("weatherSearch/$text")}) {
        }
    }
}

@Composable
fun SearchScreen(vm: WeatherViewModel,
                 navController: NavHostController,
                 paddingValues: PaddingValues,
                 context: Context){

    var hasLocationPermission by remember{ mutableStateOf(false) }
    hasLocationPermission = hasLocationPermission(context)

    if(hasLocationPermission){
        vm.getCurrentLocation()
        val currentCityResult = vm.currentLocationLiveData.observeAsState().value
        val city = currentCityResult?.get(0)?.localNames?.en
        city?.let{
            navController.navigate("weatherSearch/$it")
        }
    } else{

        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted: Boolean ->
                    hasLocationPermission = isGranted
                }
            )

        requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    if(!hasLocationPermission){
        vm.getLatestSearch()
        val latestSearch = vm.latestSearchLiveData.observeAsState().value
        if(latestSearch != null){
            navController.navigate("weatherSearch/$latestSearch")
        } else{
            SearchView(paddingValues, navController)
        }
    }
}

private fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

