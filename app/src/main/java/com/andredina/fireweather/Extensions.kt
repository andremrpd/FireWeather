package com.andredina.fireweather

import android.content.Context
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

fun Calendar.timeFormat(ctx: Context): String{
    val timeFormat = DateFormat.getTimeFormat(ctx)
    return timeFormat.format(this.time)
}

fun ViewGroup.showProgressBar(): ProgressBar {
    var progressBar = findViewWithTag<ProgressBar>("mainProgressBar")

    if (progressBar == null){
        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        val params = RelativeLayout.LayoutParams(150, 150)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        addView(progressBar, params)
        progressBar.tag = "mainProgressBar"
    }

    progressBar.visibility = View.VISIBLE

    return progressBar
}

fun ViewGroup.dismissProgressBar(){
    val progressBar = findViewWithTag<ProgressBar>("mainProgressBar")
    progressBar?.visibility = View.GONE
}

fun TextView.setTemperature(temperature: Int?){
    temperature?.let {
        text = context.getString(R.string.temp_unit, it)
    }
}

fun TextView.setSpeed(speed: Int?){
    speed?.let {
        text = context.getString(R.string.speed_unit, it)
    }
}
