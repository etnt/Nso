package se.kruskakli.nso.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import se.kruskakli.nso.domain.MainIntent
import se.kruskakli.nso.domain.MainViewModel
import se.kruskakli.nso.domain.ProcessUi
import se.kruskakli.nso.domain.SortType
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ProcessScreen(
    processes: List<ProcessUi>,
    viewModel: MainViewModel
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Divider()
            ProcSortingBar(viewModel)
            LazyColumn {
                items(items = processes) {
                    Process(it)
                }
            }
        }
    }
}

@Composable
fun Process(
    process: ProcessUi
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (process.name == "") process.pid else process.name,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(0.dp)
                .weight(2f)
        )
        Text(
            text = process.reds,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(0.dp)
                .weight(1f),
            textAlign = TextAlign.End
        )
        Text(
            text = process.memory,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(0.dp)
                .weight(1f),
            textAlign = TextAlign.End
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcSortingBar(
    viewModel: MainViewModel
) {
    var nameSortType by remember { mutableStateOf(SortType.Ascending) }
    var memSortType by remember { mutableStateOf(SortType.Ascending) }
    var redsSortType by remember { mutableStateOf(SortType.Ascending) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(0.dp),
                onClick = {
                    nameSortType =
                        if (nameSortType == SortType.Ascending) SortType.Descending else SortType.Ascending
                    viewModel.handleIntent(
                        MainIntent.SortData(
                            TabPage.EtsTables,
                            "name",
                            nameSortType
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = if (nameSortType == SortType.Ascending) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Sort Ascending",
                    modifier = Modifier
                        .padding(0.dp),
                    tint = Color.Black
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(0.dp),
                onClick = {
                    redsSortType =
                        if (redsSortType == SortType.Ascending) SortType.Descending else SortType.Ascending
                    viewModel.handleIntent(
                        MainIntent.SortData(
                            TabPage.EtsTables,
                            "reds",
                            redsSortType
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = if (redsSortType == SortType.Ascending) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Sort Descending",
                    modifier = Modifier
                        .padding(0.dp),
                    tint = Color.Black
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(0.dp),
                onClick = {
                    memSortType =
                        if (memSortType == SortType.Ascending) SortType.Descending else SortType.Ascending
                    viewModel.handleIntent(
                        MainIntent.SortData(
                            TabPage.EtsTables,
                            "mem",
                            memSortType
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = if (memSortType == SortType.Ascending) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Sort Descending",
                    modifier = Modifier
                        .padding(0.dp),
                    tint = Color.Black
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Process",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(0.dp)
                    .weight(2f)
            )
            Text(
                text = "Reductions",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .padding(0.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )
            Text(
                text = "Memory",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(0.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )
        }
        Divider()
    }
}