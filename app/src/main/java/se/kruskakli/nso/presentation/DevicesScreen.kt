package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
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
                DeviceField(label = "Last Connected", value = lastConnected)
                DeviceField(label = "Address", value = address)
                DeviceField(label = "Port", value = port)
                DeviceField(label = "Authgroup", value = authgroup)
                DeviceField(label = "Commit Queue Length", value = commitQueue.queueLength)
                DeviceField(label = "Oper State", value = state.operState)
                if (state.transactionMode != null)
                    DeviceField(label = "Transaction Mode", value = state.transactionMode)
                DeviceField(label = "Admin State", value = state.adminState)
                Alarms(alarmSummary)
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
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(top = 2.dp, bottom = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
        ) {
            Text(
                text = "Alarms:",
                style = MaterialTheme.typography.labelMedium
            )
            DeviceField(label = "Indeterminates", value = alarmSummary.indeterminates)
            DeviceField(label = "Criticals", value = alarmSummary.critical)
            DeviceField(label = "Majors", value = alarmSummary.major)
            DeviceField(label = "Minors", value = alarmSummary.minor)
            DeviceField(label = "Warnings", value = alarmSummary.warning)
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
            style = MaterialTheme.typography.labelMedium
        )
        ClickableText(
            text = text,
            onClick = { toggleShow() },
            style = TextStyle(
                color = Color.Blue
                //fontSize = 14.sp
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun DeviceField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${label}:",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

