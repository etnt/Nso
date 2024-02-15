package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class Transaction(
    val datastore: List<Datastore>
) {
    data class Datastore(
        val name: Name,
        val commit: Long?,
        @Json(name = "total-time") val totalTime: String?,
        @Json(name = "service-time") val serviceTime: String?,
        @Json(name = "validation-time") val validationTime: String?,
        @Json(name = "global-lock-time") val globalLockTime: String?,
        @Json(name = "global-lock-wait-time") val globalLockWaitTime: String?,
        val aborts: Long?,
        val conflicts: Long?,
        val retries: Long?
    ) {
        enum class Name {
            operational,
            running
        }
    }
}