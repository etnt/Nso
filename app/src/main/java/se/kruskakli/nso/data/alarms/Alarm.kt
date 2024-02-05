package se.kruskakli.nso.data.alarms

import com.squareup.moshi.Json

data class Alarm(
    val device: String,
    @Json(name = "is-cleared") val isCleared: Boolean,
    @Json(name = "last-alarm-text") val lastAlarmText: String,
    @Json(name = "last-perceived-severity") val lastPerceivedSeverity: String,
    @Json(name = "last-status-change") val lastStatusChange: String,
    @Json(name = "managed-object") val managedObject: String,
    @Json(name = "specific-problem") val specificProblem: String,
    @Json(name = "status-change") val statusChange: List<StatusChange>,
    val type: String
)