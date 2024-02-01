package se.kruskakli.nso.data.devices

import retrofit2.http.GET

interface DevicesApi {
    @GET("tailf-ncs%3Adevices")
    suspend fun getNsoDevices(): TailfNcsDevices
}