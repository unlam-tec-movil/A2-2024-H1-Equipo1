package ar.edu.unlam.mobile.scaffolding.domain.repository

import ar.edu.unlam.mobile.scaffolding.data.network.WeatherApiService
import ar.edu.unlam.mobile.scaffolding.domain.model.WeatherResponse
import javax.inject.Inject

class WeatherRepository
    @Inject
    constructor(
        private val apiService: WeatherApiService,
    ) {
        suspend fun getCurrentWeather(
            lat: String,
            long: String,
            appid: String,
        ): WeatherResponse = apiService.getCurrentWeather(lat, long, appid)
    }
