package com.andredina.fireweather.di.module

import com.andredina.fireweather.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

}