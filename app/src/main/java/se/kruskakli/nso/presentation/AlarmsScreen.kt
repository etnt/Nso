package se.kruskakli.nso.presentation

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
import se.kruskakli.nso.domain.AlarmUi
import se.kruskakli.nso.domain.DeviceUi

@Composable
fun AlarmsScreen(
    nsoAlarms: List<AlarmUi>,
    modifier: Modifier = Modifier
) {
    Alarms(nsoAlarms)
}

@Composable
fun Alarms(
    alarms: List<AlarmUi>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(items = alarms) {
            Alarm(
                device = it.device,
                isCleared = it.isCleared,
                lastAlarmText = it.lastAlarmText,
                lastPerceivedSeverity = it.lastPerceivedSeverity,
                lastStatusChange = it.lastStatusChange,
                managedObject = it.managedObject,
                specificProblem = it.specificProblem,
                statusChange = it.statusChange,
                type = it.type
            )
        }
    }
}

@Composable
fun Alarm(
    device: String,
    isCleared: String,
    lastAlarmText: String,
    lastPerceivedSeverity: String,
    lastStatusChange: String,
    managedObject: String,
    specificProblem: String,
    statusChange: List<AlarmUi.StatusChange>,
    type: String
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
            DeviceHeadField(label = "Device", value = device, toggleShow)
            if (show) {
                DeviceField(label = "Last Alarm Text", value = lastAlarmText)
                DeviceField(label = "Is Cleared", value = isCleared)
                DeviceField(label = "Last Perceived Severity", value = lastPerceivedSeverity)
                DeviceField(label = "Last Status Change", value = lastStatusChange)
                DeviceField(label = "Managed Object", value = managedObject)
                DeviceField(label = "Specific Problem", value = specificProblem)
                DeviceField(label = "Type", value = type)
                statusChange.forEach() {
                    StatusChange(statusChange = it)
                }
            }
        }
    }
}

@Composable
fun StatusChange(
    statusChange: AlarmUi.StatusChange,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
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
                text = "Status Change:",
                style = MaterialTheme.typography.labelMedium
            )
            DeviceField(label = "Alarm Text", value = statusChange.alarmText)
            DeviceField(label = "Event Time", value = statusChange.eventTime)
            DeviceField(label = "Perceived Severity", value = statusChange.perceivedSeverity)
            DeviceField(label = "Received Time", value = statusChange.receivedTime)
        }
    }

}
