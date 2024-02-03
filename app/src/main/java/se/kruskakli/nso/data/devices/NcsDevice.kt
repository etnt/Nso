package se.kruskakli.nsopackages.data.devices

import com.squareup.moshi.Json

data class NcsDevice(
    val name: String,
    @Json(name = "last-connected") val lastConnected: String,
    val address: String,
    val port: Int,
    val authgroup: String,
    @Json(name = "commit-queue") val commitQueue: CommitQueue,
    val state: State,
    @Json(name = "tailf-ncs-alarms:alarm-summary") val alarmSummary: TailfNcsAlarmsAlarmSummary
)