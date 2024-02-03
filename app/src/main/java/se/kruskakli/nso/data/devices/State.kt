package se.kruskakli.nsopackages.data.devices

import com.squareup.moshi.Json

// Show states for the device
data class State(
    @Json(name = "oper-state") val operState: String,               // The actual operational state of the device
    @Json(name = "transaction-mode") val transactionMode: String,   // Indicate datastore capabilities
    @Json(name = "admin-state") val adminState: String              // Controls configuration and southbound communication
)