package se.kruskakli.nso.data.devices

import retrofit2.http.GET
import se.kruskakli.nsopackages.data.devices.TailfNcsDevices

interface DevicesApi {
    @GET("tailf-ncs%3Adevices?depth=4")
    suspend fun getNsoDevices(): TailfNcsDevices
}