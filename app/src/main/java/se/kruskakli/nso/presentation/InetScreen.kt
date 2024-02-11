package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.kruskakli.nso.data.debug.inet.Inet
import se.kruskakli.nso.domain.InetUi
import se.kruskakli.nso.domain.MainIntent


@Composable
fun InetScreen(
    nsoInet: List<InetUi>,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Divider()
        LazyColumn {
            items(items = nsoInet) {
                Inet(it)
            }
        }
    }
}


@Composable
fun Inet(
    inet: InetUi,
    modifier: Modifier = Modifier
) {
    var show by remember { mutableStateOf(false) }
    val toggleShow = { show = !show }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Divider()
            InetHeadField(inet, toggleShow)
            if (show) {
                val fields = listOf(
                    Field("Foreign Adress", inet.foreignAddress),
                    Field("Local Adress", inet.localAddress),
                    Field("Module", inet.module),
                    Field("Owner", inet.owner),
                    Field("Port", inet.port),
                    Field("Received", inet.received),
                    Field("Sent", inet.sent),
                    Field("State", inet.state),
                    Field("Type", inet.type)
                )
                InsideCard(
                    header = "Network Listeners",
                    fields = fields,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
            Divider()
        }
    }
}

@Composable
fun InetHeadField(
    inet: InetUi,
    toggleShow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { toggleShow() })
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = inet.localAddress,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp)
        )
        Text(
            text = inet.foreignAddress,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = inet.state,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(end = 4.dp)
        )
    }
}