package com.andredina.fireweather.ui.weather

import android.arch.lifecycle.ViewModel
import com.andredina.fireweather.model.Weather
import com.andredina.fireweather.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    fun getWeather(): Observable<Weather> = weatherRepository.getWeather()

}