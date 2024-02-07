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
                    FieldComponent(Field("Last Connected", lastConnected))
                    FieldComponent(Field("Address", address))
                    FieldComponent(Field("Port", port))
                    FieldComponent(Field("Authgroup", authgroup))
                    FieldComponent(Field("Commit Queue Length", commitQueue.queueLength))
                    FieldComponent(Field("Oper State", state.operState))
                    if (state.transactionMode != null)
                        FieldComponent(Field("Transaction Mode", state.transactionMode))
                    FieldComponent(Field("Admin State", state.adminState))
                    Alarms(alarmSummary)
                }
            }
        }
    }
}

@Composable
fun Alarms(
    alarmSummary: TailfNcsAlarmsAlarmSummaryUI,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
        ) {
            Text(
                text = "Alarms:",
                style = MaterialTheme.typography.titleSmall
            )
            FieldComponent(Field("Indeterminates", alarmSummary.indeterminates))
            FieldComponent(Field("Criticals", alarmSummary.critical))
            FieldComponent(Field("Majors", alarmSummary.major))
            FieldComponent(Field("Minors", alarmSummary.minor))
            FieldComponent(Field("Warnings", alarmSummary.warning))
        }
    }

}

@Composable
fun DeviceHeadField(
    label: String,
    value: String,
    toggleShow: () -> Unit,
    modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 12.sp)) {
            append(value)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${label}:",
            style = MaterialTheme.typography.titleSmall
        )
        ClickableText(
            text = text,
            onClick = { toggleShow() },
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Blue),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}



