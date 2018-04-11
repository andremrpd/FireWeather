package com.andredina.fireweather.di.module

import android.app.Application
import android.content.Context
import com.andredina.fireweather.BuildConfig
import com.andredina.fireweather.service.DateAdapter
import com.andredina.fireweather.service.OpenWeatherService
import com.patloew.rxlocation.RxLocation
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [ViewModelModule::class])
class AppModule{

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }

    @Provides fun provideContext(application: Application): Context = application.applicationContext

    @Provides fun providesWeatherService(retrofit: Retrofit): OpenWeatherService
            = retrofit.create(OpenWeatherService::class.java)

    @Provides fun providesRetrofit(okHttpClient: OkHttpClient,
                                   converterFactory: Converter.Factory,
                                   callAdapterFactory: CallAdapter.Factory): Retrofit
            = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()

    @Provides fun providesConverterFactory(moshi: Moshi): Converter.Factory
            = MoshiConverterFactory.create(moshi)

    @Provides fun providesMoshi(): Moshi
            = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateAdapter())
            .build()

    @Provides fun providesCallAdapterFactory(): CallAdapter.Factory
            = RxJava2CallAdapterFactory.create()


    @Provides fun providesHttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

    }

    @Provides fun providesRxLocation(context: Context) = RxLocation(context)


}