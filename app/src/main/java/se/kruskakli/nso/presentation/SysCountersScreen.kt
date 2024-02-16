package se.kruskakli.nso.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import se.kruskakli.nso.domain.SysCountersUi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import se.kruskakli.nso.domain.SysCountersUi.TransactionUi.DatastoreUi
import se.kruskakli.nso.domain.SysCountersUi.SessionUi
import se.kruskakli.nso.domain.SysCountersUi.DeviceUi


@Composable
fun SysCountersScreen(
    nsoSysCounters: SysCountersUi?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Divider()
            LazyColumn {
                item { Transaction(nsoSysCounters?.transaction) }
                //ServiceConflicts { nsoSysCounters?.serviceConflicts) }
                //Cdb { nsoSysCounters?.cdb }
                item { Device(nsoSysCounters?.device) }
                item { Session(nsoSysCounters?.session) }
            }
        }
    }
}

@Composable
fun Device(
    device: DeviceUi?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        val cards: List<@Composable () -> Unit> = listOf(
            {
                InsideCardWithHelp(
                    header = "Devices:",
                    fields = listOf(
                        device?.connect.let {
                            FieldWithHelp("Connect:", it ?: "0", DeviceUi.CONNECT_DESCRIPTION)
                        },
                        device?.connectFailed.let {
                            FieldWithHelp("Connect fail:", it ?: "0", DeviceUi.CONNECT_FAILED_DESCRIPTION)
                        },
                        device?.syncFrom.let {
                            FieldWithHelp("Sync from:", it ?: "0", SysCountersUi.DeviceUi.SYNC_FROM_DESCRIPTION)
                        },
                        device?.syncTo.let {
                            FieldWithHelp("Sync to:", it ?: "0", SysCountersUi.DeviceUi.SYNC_TO_DESCRIPTION)
                        },
                        device?.outOfSync.let {
                            FieldWithHelp("Out of sync:", it ?: "0", SysCountersUi.DeviceUi.OUT_OF_SYNC_DESCRIPTION)
                        }
                    ),
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        )
        OutlinedCards(
            header = "Device Counters:",
            fields = emptyList(),
            cards = cards,
            textColor = MaterialTheme.colorScheme.onSurface,
            color = MaterialTheme.colorScheme.surface,
            show = false
        )
    }
}

@Composable
fun Session(
    session: SessionUi?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        val cards: List<@Composable () -> Unit> = listOf(
            {
                InsideCardWithHelp(
                    header = "Sessions:",
                    fields = listOf(
                        session?.total.let {
                            FieldWithHelp("Total:", it ?: "0", SessionUi.TOTAL_DESCRIPTION)
                        },
                        session?.netconfTotal.let {
                            FieldWithHelp("Netconf total:", it ?: "0", SessionUi.NETCONF_TOTAL_DESCRIPTION)
                        },
                        session?.restconfTotal.let {
                            FieldWithHelp("Restconf total:", it ?: "0", SessionUi.RESTCONF_TOTAL_DESCRIPTION)
                        },
                        session?.jsonrpcTotal.let {
                            FieldWithHelp("Jsonrpc total:", it ?: "0", SessionUi.JSONRPC_TOTAL_DESCRIPTION)
                        },
                        session?.snmpTotal.let {
                            FieldWithHelp("Snmp total:", it ?: "0", SessionUi.SNMP_TOTAL_DESCRIPTION)
                        },
                        session?.cliTotal.let {
                            FieldWithHelp("Cli total:", it ?: "0", SessionUi.CLI_TOTAL_DESCRIPTION)
                        }
                    ),
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        )
        OutlinedCards(
            header = "Session Counters:",
            fields = emptyList(),
            cards = cards,
            textColor = MaterialTheme.colorScheme.onSurface,
            color = MaterialTheme.colorScheme.surface,
            show = false
        )
    }
}

@Composable
fun Transaction(
    transaction: SysCountersUi.TransactionUi?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        val ds: List<@Composable () -> Unit> = transaction?.datastore?.map { datastore ->
            {
            InsideCardWithHelp(
                header = "${datastore.name}:",
                fields = listOf(
                    datastore.commit?.let {
                        FieldWithHelp("Commit:", "${it}", DatastoreUi.COMMIT_DESCRIPTION)
                    },
                    datastore.totalTime?.let {
                        FieldWithHelp("Total time:", "${it}", DatastoreUi.TOTAL_TIME_DESCRIPTION)
                    },
                    datastore.serviceTime?.let {
                        FieldWithHelp("Service time:", "${it}", DatastoreUi.SERVICE_TIME_DESCRIPTION)
                    },
                    datastore.validationTime?.let {
                        FieldWithHelp("Validation time:", "${it}", DatastoreUi.VALIDATION_TIME_DESCRIPTION)
                    },
                    datastore.globalLockTime?.let {
                        FieldWithHelp("Global lock time:", "${it}", DatastoreUi.GLOBAL_LOCK_TIME_DESCRIPTION)
                    },
                    datastore.globalLockWaitTime?.let {
                        FieldWithHelp("Global lock wait time:", "${it}", DatastoreUi.GLOBAL_LOCK_WAIT_TIME_DESCRIPTION)
                    },
                    datastore.aborts?.let {
                        FieldWithHelp("Aborts:", "${it}", DatastoreUi.ABORTS_DESCRIPTION)
                    },
                    datastore.conflicts?.let {
                        FieldWithHelp("Conflicts:", "${it}", DatastoreUi.CONFLICTS_DESCRIPTION)
                    },
                    datastore.retries?.let {
                        FieldWithHelp("Retries:", "${it}", DatastoreUi.RETRIES_DESCRIPTION)
                    },
                ),
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
        } 
    } ?: emptyList()
        OutlinedCards(
            header = "Transactional Datastores:",
            fields = emptyList(),
            cards = ds,
            textColor = MaterialTheme.colorScheme.onSurface,
            color = MaterialTheme.colorScheme.surface,
            show = false
        )
    }
}

