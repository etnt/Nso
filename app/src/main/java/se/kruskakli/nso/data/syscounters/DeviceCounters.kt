package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class DeviceCounters(
    val connect: Long?,
    @Json(name = "connect-failed") val connectFailed: Long?,
    @Json(name = "sync-from") val syncFrom: Long?,
    @Json(name = "sync-to") val syncTo: Long?,
    @Json(name = "out-of-sync") val outOfSync: Long?
) {
    companion object {
        const val CONNECT_DESCRIPTION = "Total number of device connects."
        const val CONNECT_FAILED_DESCRIPTION = "Total number of failed device connects."
        const val SYNC_FROM_DESCRIPTION = "Total number of sync-from invocations."
        const val SYNC_TO_DESCRIPTION = "Total number of sync-to invocations."
        const val OUT_OF_SYNC_DESCRIPTION = "Total number of out of sync detections."
    }
}
