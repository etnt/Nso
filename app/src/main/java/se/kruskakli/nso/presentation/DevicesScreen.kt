package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.kruskakli.nso.domain.DeviceUi
import se.kruskakli.nso.domain.DeviceUi.*
import se.kruskakli.nsopackages.data.devices.CommitQueue

@Composable
fun DevicesScreen(
    nsoDevices: List<DeviceUi>,
    modifier: Modifier = Modifier
) {
    Devices(nsoDevices)
}

@Composable
fun Devices(
    devices: List<DeviceUi>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(items = devices) {
            Log.d("NsoDevicesScreen", "it: ${it}")
            Device(
                name = it.name,
                lastConnected = it.lastConnected,
                address = it.address,
                port = it.port,
                authgroup = it.authgroup,
                commitQueue = it.commitQueue,
                state = it.state,
                alarmSummary = it.alarmSummary
            )
        }
    }
}

@Composable
fun Device(
    name: String,
    lastConnected: String,
    address: String,
    port: String,
    authgroup: String,
    commitQueue: CommitQueueUI,
    state: StateUI,
    alarmSummary: TailfNcsAlarmsAlarmSummaryUI,
    modifier: Modifier = Modifier
) {
    var show by remember { mutableStateOf(false) }
    val toggleShow = { show = !show }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                DeviceHeadField(label = "Device", value = name, toggleShow)
                if (show) {
                    val fields = mutableListOf(
                        Field("Last Connected", lastConnected),
                        Field("Address", address),
                        Field("Port", port),
                        Field("Authgroup", authgroup),
                        Field("Commit Queue Length", commitQueue.queueLength),
                        Field("Oper State", state.operState),
                        Field("Admin State", state.adminState)
                    )
                    state.transactionMode?.let {
                        fields.add(Field("Transaction Mode", it))
                    }
                    fields.forEach { field ->
                        FieldComponent(field)
                    }
                    val alarmFields = listOf(
                        Field("Indeterminates", alarmSummary.indeterminates),
                        Field("Criticals", alarmSummary.critical),
                        Field("Majors", alarmSummary.major),
                        Field("Minors", alarmSummary.minor),
                        Field("Warnings", alarmSummary.warning)
                    )
                    InsideCard("Status Change:", alarmFields)
                }
            }
        }
    }
}





