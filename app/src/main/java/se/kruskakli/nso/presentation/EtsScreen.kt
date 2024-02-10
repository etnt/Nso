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
import androidx.compose.ui.unit.dp
import se.kruskakli.nso.domain.EtsUi
import se.kruskakli.nso.domain.InetUi

@Composable
fun EtsScreen(
    nsoEts: List<EtsUi>
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Divider()
        LazyColumn {
            items(items = nsoEts) {
                Ets(it)
            }
        }
    }
}

@Composable
fun Ets(
    ets: EtsUi,
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
            EtsHeadField(ets, toggleShow)
            if (show) {
                val fields = listOf(
                    Field("Table Name", ets.name),
                    Field("Table Memory", ets.mem),
                    Field("Table Size", ets.size),
                    Field("Table Type", ets.type),
                    Field("Table Owner", ets.owner),
                    Field("Table ID", ets.id)
                )
                InsideCard(
                    header = "ETS tables:",
                    fields = fields,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}


@Composable
fun EtsHeadField(
    ets: EtsUi,
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
            text = ets.name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp)
        )
        Text(
            text = ets.mem,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = ets.type,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(end = 4.dp)
        )
    }
}