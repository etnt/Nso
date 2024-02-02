package se.kruskakli.nso.domain

data class NcsDeviceUI(
    val name: String,
    val lastConnected: String,
    val address: String,
    val port: Int,
    val authgroup: String,
    val commitQueue: CommitQueueUI,
    val state: StateUI,
    val alarmSummary: TailfNcsAlarmsAlarmSummaryUI
) {
    data class CommitQueueUI(
        val queueLength: Int
    )

    data class StateUI(
        val operState: String,
        val transactionMode: String,
        val adminState: String
    )

    data class TailfNcsAlarmsAlarmSummaryUI(
        val critical: Int,
        val major: Int,
        val minor: Int,
        val warning: Int
    )
}
