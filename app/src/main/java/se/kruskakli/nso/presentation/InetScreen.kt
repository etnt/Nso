package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import se.kruskakli.nso.data.debug.inet.Inet
import se.kruskakli.nso.domain.InetUi


@Composable
fun InetScreen(
    nsoInet: List<InetUi>,
    modifier: Modifier = Modifier
) {

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
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
                fields.forEach { field ->
                    FieldComponent(field)
                }
            }
        }
    }
}
