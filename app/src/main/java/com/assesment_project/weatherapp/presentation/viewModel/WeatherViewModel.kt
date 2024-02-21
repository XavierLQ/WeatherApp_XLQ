package com.assesment_project.weatherapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assesment_project.weatherapp.data.model.WeatherResult
import com.assesment_project.weatherapp.domain.repository.UseCaseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(private val useCaseRepo: UseCaseRepo): ViewModel() {

    private val _weatherLiveData:MutableLiveData<WeatherResult> = MutableLiveData()
    val weatherLiveData:LiveData<WeatherResult> get() = _weatherLiveData

    fun getWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<WeatherResult> = useCaseRepo.getWeather(city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherLiveData.postValue(it)

                    } ?: throw Exception("Response was null")
                } else {
                    throw Exception("Response was unsuccessful")
                }
            } catch (e: Exception) { //Do something }
            }
        }
    }
}