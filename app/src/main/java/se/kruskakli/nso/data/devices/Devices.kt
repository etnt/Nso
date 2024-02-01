package se.kruskakli.nsopackages.data.devices

import com.squareup.moshi.Json

data class Devices {
    @Json(name = "device") val devices: List<NsoDevice>
}
