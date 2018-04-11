package com.andredina.fireweather.service

import android.annotation.SuppressLint
import android.location.Location
import com.andredina.fireweather.model.Position
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import io.reactivex.Observable
import javax.inject.Inject

class LocationService @Inject constructor(val rxLocation: RxLocation) {

    private val locationRequest: LocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000) //60s
            .setSmallestDisplacement(500F)

    fun getPosition(): Observable<Position>
        = rxLocation.settings().checkAndHandleResolution(locationRequest)
            .flatMapObservable(this::getPositionObservable)

    @SuppressLint("MissingPermission")
    private fun getPositionObservable(success: Boolean): Observable<Position>{

        return if (success){
             rxLocation.location().updates(locationRequest)
                    .map(this::getPositionFromLocation)

        }else {
             rxLocation.location().lastLocation()
                    .map(this::getPositionFromLocation)
                    .toObservable()
        }

    }

    private fun getPositionFromLocation(location: Location): Position = Position(location.latitude, location.longitude)

}