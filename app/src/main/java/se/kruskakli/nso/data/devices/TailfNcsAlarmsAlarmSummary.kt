package se.kruskakli.nsopackages.data.devices

data class TailfNcsAlarmsAlarmSummary {
    val indeterminates: Int,
    val criticals: Int,
    val majors: Int,
    val minors: Int,
    val warnings: Int
}