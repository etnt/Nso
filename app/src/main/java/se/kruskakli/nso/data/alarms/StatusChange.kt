package se.kruskakli.nso.data.alarms

import com.squareup.moshi.Json

data class StatusChange(
    @Json(name = "alarm-text") val alarmText: String,
    @Json(name = "event-time") val eventTime: String,
    @Json(name = "perceived-severity") val perceivedSeverity: String,
    @Json(name = "received-time") val receivedTime: String
)