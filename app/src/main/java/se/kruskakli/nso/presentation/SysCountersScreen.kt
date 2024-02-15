package se.kruskakli.nso.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import se.kruskakli.nso.domain.SysCountersUi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            Transaction(nsoSysCounters?.transaction)
            //ServiceConflicts(nsoSysCounters?.serviceConflicts)
            //Cdb(nsoSysCounters?.cdb)
            //Device(nsoSysCounters?.device)
            //Session(nsoSysCounters?.session)
        }
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
        Text("Transactional Datastores")
        transaction?.datastore?.forEach { datastore ->
            Datastore(datastore)
        }
    }
}

@Composable
fun Datastore(
    datastore: SysCountersUi.TransactionUi.DatastoreUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Name: ${datastore.name}")
        Text("Commit: ${datastore.commit}")
        datastore.totalTime?.let {
            Text("Total time: ${datastore.totalTime}")
        }
        datastore.serviceTime?.let {
            Text("Service time: ${datastore.serviceTime}")
        }
        datastore.validationTime?.let {
            Text("Validation time: ${datastore.validationTime}")
        }
        datastore.globalLockTime?.let {
            Text("Global lock time: ${datastore.globalLockTime}")
        }
        datastore.globalLockWaitTime?.let {
            Text("Global lock wait time: ${datastore.globalLockWaitTime}")
        }
        datastore.aborts?.let {
            Text("Aborts: ${datastore.aborts}")
        }
        datastore.conflicts?.let {
            Text("Conflicts: ${it}")
        }
        datastore.retries?.let {
            Text("Retries: ${it}")
        }
    }
}