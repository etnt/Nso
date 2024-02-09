package se.kruskakli.nso.data.debug.inet

import com.squareup.moshi.Json

data class All(
    @Json(name = "foreign_address") val foreignAddress: String,
    @Json(name = "local_address") val localAddress: String,
    val module: String,
    val owner: String,
    val port: String,
    @Json(name = "recv") val received: String,
    val sent: String,
    val state: String,
    val type: String
)