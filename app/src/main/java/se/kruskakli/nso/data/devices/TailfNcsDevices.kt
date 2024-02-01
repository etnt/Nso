package se.kruskakli.nsopackages.data.devices

import com.squareup.moshi.Json

// Top node of the returned JSON from the NSO Restconf API
data class TailfNcsDevices {
    @Json(name = "tailf-ncs:devices") val nsoDevices: Devices
}