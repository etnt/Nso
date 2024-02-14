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

        companion object {
            const val NAME_DESCRIPTION = "Datastore name: 'operational' or 'running'"
            const val COMMIT_DESCRIPTION = "Total number of committed transactions."
            const val TOTAL_TIME_DESCRIPTION = "Total amount of milliseconds spent in successful transactions."
            const val SERVICE_TIME_DESCRIPTION = "Total amount of milliseconds spent in service execution."
            const val VALIDATION_TIME_DESCRIPTION = "Total amount of milliseconds spent in transaction validation."
            const val GLOBAL_LOCK_TIME_DESCRIPTION = "Total amount of milliseconds spent in global lock."
            const val GLOBAL_LOCK_WAIT_TIME_DESCRIPTION = "Total amount of milliseconds spent waiting for the global lock."
            const val ABORTS_DESCRIPTION = "Total number of aborted transactions."
            const val CONFLICTS_DESCRIPTION = "Total number of transaction conflicts for running."
            const val RETRIES_DESCRIPTION = "Total number of transaction retries for running."
        }
    }
}