package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class ServiceConflicts(
    val serviceType: List<ServiceType?>
) {
    data class ServiceType(
        val name: String,
        val conflicts: Long
    ) {
        companion object {
            const val NAME_DESCRIPTION = "Type of the service."
            const val CONFLICTS_DESCRIPTION = "Number of transaction conflicts with this service type."
        }
    }
}
