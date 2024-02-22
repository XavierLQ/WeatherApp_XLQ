package com.assesment_project.weatherapp.presentation.ui

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.assesment_project.weatherapp.presentation.theme.WeatherAppTheme
import com.assesment_project.weatherapp.presentation.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: WeatherViewModel = viewModel()
                    val navController = rememberNavController()

                    HomeScreen(viewModel = viewModel, navController = navController, applicationContext)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: WeatherViewModel, navController: NavHostController, context: Context){

    var showBackButton by remember{ mutableStateOf(false)}
    var appBarTitle by remember{ mutableStateOf("Home") }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text(appBarTitle, color = MaterialTheme.colorScheme.primary)},
                colors = topAppBarColors( containerColor = MaterialTheme.colorScheme.primaryContainer),
                navigationIcon = {
                    if(showBackButton) {
                        IconButton(onClick = {navController.navigateUp()}){
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary) }
                    }
                }
            )
        },

        content = {paddingValues ->

            NavHost(navController = navController, startDestination = "searchScreen"){
                composable("searchScreen"){
                    showBackButton = false
                    appBarTitle = "Search Screen"
                    SearchScreen(viewModel, navController, paddingValues, context)
                }
                composable("weatherSearch/{city}",
                    arguments = listOf(navArgument("city"){type = NavType.StringType})
                ){
                    showBackButton = true
                    val city = it.arguments?.getString("city")!!
                    appBarTitle = city
                    WeatherDetailScreen(city, navController, paddingValues, viewModel)
                }
            }
        }
    )
}




