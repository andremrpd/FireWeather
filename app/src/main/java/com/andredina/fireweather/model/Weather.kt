package com.andredina.fireweather.model

import com.squareup.moshi.Json
import java.util.*

data class Weather(@Json(name = "weather") var conditions: List<Condition>?,
                   @Json(name = "main") var temperature: Temperature?,
                   var wind: Wind?,
                   @Json(name = "sys") var sun: Sun?,
                   @Json(name = "name") var city: String?){

    companion object {
        const val UNIT_METRIC = "metric"
        const val UNIT_IMPERIAL = "imperial"
    }

}

data class Condition(var main: String,
                     var description: String,
                     var icon: String)

data class Temperature(var temp: Double,
                       @Json(name = "temp_min") var tempMin: Double,
                       @Json(name = "temp_max") var tempMax: Double,
                       var humidity: Int)

data class Wind(var speed: Double,
                var deg: Int)

data class Sun(var sunrise: Calendar,
               var sunset: Calendar)

data class Position(var lat: Double,
                    var lon: Double)

