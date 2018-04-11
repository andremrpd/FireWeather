package com.andredina.fireweather.repository

import android.util.Log
import com.andredina.fireweather.model.Position
import com.andredina.fireweather.model.Weather
import com.andredina.fireweather.service.LocationService
import com.andredina.fireweather.service.OpenWeatherService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val openWeatherService: OpenWeatherService,
                                            private val locationService: LocationService) {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    external fun getAppID(): String

    fun getWeather(): Observable<Weather>
        = locationService.getPosition()
            .flatMapSingle(this::getWetherSingle)

    private fun getWetherSingle(position: Position): Single<Weather>
        = openWeatherService.getWeather(getAppID(), position.lat, position.lon, Weather.UNIT_METRIC)
            .subscribeOn(Schedulers.io())

}
