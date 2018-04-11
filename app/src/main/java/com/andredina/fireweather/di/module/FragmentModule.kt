package com.andredina.fireweather.di.module

import com.andredina.fireweather.ui.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeUserProfileFragment(): WeatherFragment

}