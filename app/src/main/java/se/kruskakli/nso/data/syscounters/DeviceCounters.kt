package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class DeviceCounters(
    val connect: Long?,
    @Json(name = "connect-failed") val connectFailed: Long?,
    @Json(name = "sync-from") val syncFrom: Long?,
    @Json(name = "sync-to") val syncTo: Long?,
    @Json(name = "out-of-sync") val outOfSync: Long?
) 

