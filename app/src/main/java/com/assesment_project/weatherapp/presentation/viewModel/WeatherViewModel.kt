package com.assesment_project.weatherapp.presentation.viewModel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.domain.repository.UseCaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val useCaseRepo: UseCaseRepo): ViewModel() {

    //weather live data
    private val _weatherLiveData:MutableLiveData<WeatherResult> = MutableLiveData()
    val weatherLiveData:LiveData<WeatherResult> get() = _weatherLiveData

    //device location live data
    private val _currentLocationLiveData:MutableLiveData<String?> = MutableLiveData()
    val currentLocationLiveData:LiveData<String?> get() = _currentLocationLiveData

    //latest search live data
    private val _latestSearchLiveData:MutableLiveData<String?> = MutableLiveData()
    val latestSearchLiveData:LiveData<String?> get() = _latestSearchLiveData

    fun getWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<WeatherResult> = useCaseRepo.getWeather(city)
                useCaseRepo.storeCitySearch(city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherLiveData.postValue(it)

                    } ?: throw Exception("Response was null")
                } else {
                    throw Exception("Response was unsuccessful")
                }
            } catch (e: Exception) {
            //Do something
            }
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val location: String? = useCaseRepo.getLocation()

                location.let {
                    _currentLocationLiveData.postValue(it)
                } ?: throw Exception("Location was null")
            } catch (e: Exception) {
            //Do something
            }
        }
    }

    fun getLatestSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val searchedCity: String? = useCaseRepo.getPreviouslySearchedCity()
                //If not null, there is a stored previous search, and if null then there is no previous search
                searchedCity?.let {
                    _latestSearchLiveData.postValue(it)
                }
            } catch (e: Exception) {
            //Do something
            }
        }
    }
}