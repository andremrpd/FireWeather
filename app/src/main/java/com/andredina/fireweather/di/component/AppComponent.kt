package com.andredina.fireweather.di.component

import android.app.Application
import com.andredina.fireweather.App
import com.andredina.fireweather.di.module.ActivityModule
import com.andredina.fireweather.di.module.AppModule
import com.andredina.fireweather.di.module.FragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityModule::class, FragmentModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}