package se.kruskakli.nso.data.alarms

import com.squareup.moshi.Json

data class TailfNcsAlarmsAlarmList(
    val alarm: List<Alarm>,
    @Json(name = "last-changed") val lastChanged: String,
    @Json(name = "number-of-alarms") val numberOfAlarms: Int
)