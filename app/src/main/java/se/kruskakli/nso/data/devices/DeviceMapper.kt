package se.kruskakli.nso.data.devices

import se.kruskakli.nso.domain.DeviceUi
import se.kruskakli.nsopackages.data.devices.NcsDevice

fun NcsDevice.toDeviceUi(): DeviceUi {
    val nsoCommitQueue = DeviceUi.CommitQueueUI(
        commitQueue.queueLength
    )
    val deviceState = DeviceUi.StateUI(
        state.operState,
        state.transactionMode,
        state.adminState
    )
    val nsoAlarmSummary = DeviceUi.TailfNcsAlarmsAlarmSummaryUI(
        alarmSummary.indeterminates,
        alarmSummary.criticals,
        alarmSummary.majors,
        alarmSummary.minors,
        alarmSummary.warnings
    )
    return DeviceUi(
        name = name,
        lastConnected = lastConnected,
        address = address,
        port = port,
        authgroup = authgroup,
        commitQueue = nsoCommitQueue,
        state = deviceState,
        alarmSummary = nsoAlarmSummary
    )
}