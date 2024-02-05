package se.kruskakli.nso.data.alarms

import com.squareup.moshi.Json

data class NsoAlarmList(
    @Json(name = "tailf-ncs-alarms:alarm-list") val nsoAlarmList: TailfNcsAlarmsAlarmList
)