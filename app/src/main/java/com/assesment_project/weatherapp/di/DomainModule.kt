package com.assesment_project.weatherapp.di

import android.content.Context
import com.assesment_project.weatherapp.data.location.LocationRepo
import com.assesment_project.weatherapp.data.restApi.NetworkRepository
import com.assesment_project.weatherapp.data.roomDb.CityDatabase
import com.assesment_project.weatherapp.domain.repository.UseCaseRepo
import com.assesment_project.weatherapp.domain.repository.UseCaseRepoImpl
import com.assesment_project.weatherapp.domain.useCases.GetDeviceLocationCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetPreviouslySearchedCityUseCase
import com.assesment_project.weatherapp.domain.useCases.GetWeatherByCityUseCase
import com.assesment_project.weatherapp.domain.useCases.StoreSearchedCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ViewModelComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideLocationCityUseCase(locationRepo: LocationRepo) =
        GetDeviceLocationCityUseCase(locationRepo)

    @Provides
    fun providePreviouslySearchedCityUseCase(@ApplicationContext context: Context, cityDB: CityDatabase) =
        GetPreviouslySearchedCityUseCase(context, cityDB)

    @Provides
    fun provideWeatherByCity(networkRepo: NetworkRepository) =
        GetWeatherByCityUseCase(networkRepo)

    @Provides
    fun provideStoreSearchedCity(@ApplicationContext context: Context, cityDB: CityDatabase) =
        StoreSearchedCityUseCase(context, cityDB)


    @Provides
    fun provideRepository(deviceLocationUseCase: GetDeviceLocationCityUseCase,
                          previousSearchUseCase: GetPreviouslySearchedCityUseCase,
                          weatherUseCase: GetWeatherByCityUseCase,
                          storeSearchedCityUseCase: StoreSearchedCityUseCase
    ): UseCaseRepo = UseCaseRepoImpl(deviceLocationUseCase, previousSearchUseCase, weatherUseCase,
            storeSearchedCityUseCase)

}