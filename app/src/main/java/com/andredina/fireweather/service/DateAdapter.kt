package com.andredina.fireweather.service

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class DateAdapter {

    @FromJson fun fromJson(unix: String): Calendar? {
        val date = Calendar.getInstance()
        date.timeInMillis = unix.toLong() * 1000L
        return date
    }

    @ToJson fun toJson(date: Calendar): String = (date.timeInMillis/1000).toString()
}