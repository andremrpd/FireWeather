package com.andredina.fireweather.ui.weather

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andredina.fireweather.R
import com.andredina.fireweather.model.Weather
import com.andredina.fireweather.setSpeed
import com.andredina.fireweather.setTemperature
import com.andredina.fireweather.timeFormat
import com.andredina.fireweather.ui.base.BaseFragment
import com.andredina.fireweather.ui.base.BaseView
import com.bumptech.glide.Glide
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.kotlin.autoDisposable
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherFragment : BaseFragment(), BaseView {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureDagger()
        configureViewModel()
        showLoading()
    }

    fun configureViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
        viewModel.getWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .autoDisposable(lifecycle.scope())
                .subscribe (this::updateUI, this::onError)
    }

    private fun configureDagger() {
        AndroidSupportInjection.inject(this)
    }

    fun updateUI(weather: Weather){
        dismissLoading()
        weather.conditions?.get(0)?.icon?.let {
            val iconUrl = getString(R.string.icon_url, it)
            Glide.with(this)
                    .load(iconUrl)
                    .into(imgIcon)
        }
        txtCity.text = weather.city
        txtCondition.text = weather.conditions?.get(0)?.main
        txtTemp.setTemperature(weather.temperature?.temp?.roundToInt())
        txtTempMin.setTemperature(weather.temperature?.tempMin?.roundToInt())
        txtTempMax.setTemperature(weather.temperature?.tempMax?.roundToInt())
        txtHumidity.text = "${weather.temperature?.humidity}%"
        txtWind.setSpeed(weather.wind?.speed?.roundToInt())
        txtSunrise.text = weather.sun?.sunrise?.timeFormat(requireContext())
        txtSunset.text = weather.sun?.sunset?.timeFormat(requireContext())
    }


}