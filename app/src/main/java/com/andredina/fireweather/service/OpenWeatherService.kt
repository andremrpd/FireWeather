package com.andredina.fireweather.service

import com.andredina.fireweather.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("weather")
    fun getWeather(@Query("APPID") appid: String,
                   @Query("lat") lat: Double,
                   @Query("lon") lon: Double,
                   @Query("units") units: String)
            : Single<Weather>

}