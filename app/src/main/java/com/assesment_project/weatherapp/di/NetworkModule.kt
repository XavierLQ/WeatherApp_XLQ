package com.assesment_project.weatherapp.di

import com.assesment_project.weatherapp.data.restApi.NetworkClient
import com.assesment_project.weatherapp.data.restApi.NetworkRepoImpl
import com.assesment_project.weatherapp.data.restApi.NetworkRepository
import com.assesment_project.weatherapp.data.restApi.RestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideRepo(): NetworkClient = NetworkClient()

    @Provides
    fun provideInterceptor(networkClient: NetworkClient): HttpLoggingInterceptor =
        networkClient.getInterceptor()

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor
                            , networkClient: NetworkClient): OkHttpClient =
        networkClient.getOkHttpClient(interceptor)

    @Provides
    fun provideNetworkService(okHttpClient: OkHttpClient
                              , networkClient: NetworkClient): RestAPI =
        networkClient.getNetworkService(okHttpClient)

    @Provides
    fun providesNetworkRepo(networkAPI: RestAPI): NetworkRepository =
        NetworkRepoImpl(networkAPI)
}