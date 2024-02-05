package se.kruskakli.nso.data

import retrofit2.http.GET
import se.kruskakli.nso.data.packages.NsoPackages
import se.kruskakli.nsopackages.data.devices.TailfNcsDevices

interface NsoApi {
    @GET("tailf-ncs%3Apackages")
    suspend fun getPackages(): NsoPackages

    @GET("tailf-ncs%3Adevices?depth=4")
    suspend fun getNsoDevices(): TailfNcsDevices
}