package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class ServiceConflicts(
    val serviceType: List<ServiceType?>
) {
    data class ServiceType(
        val name: String?,
        val conflicts: Long?
    ) 
}
