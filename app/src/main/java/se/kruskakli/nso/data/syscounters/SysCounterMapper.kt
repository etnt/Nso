package se.kruskakli.nso.data.syscounters

import se.kruskakli.nso.domain.CounterUi

fun SysCounters.toUiModel(): CounterUi {
    return CounterUi(
        transaction = transaction.toUiModel(),
        serviceConflicts = serviceConflicts.toUiModel(),
        cdb = cdb.toUiModel(),
        device = device.toUiModel(),
        session = session.toUiModel()
    )
}


// Add this function to the Transaction class
fun Transaction.toUiModel(): CounterUi.TransactionUi {
    return CounterUi.TransactionUi(
        datastore = datastore.map { it.toUiModel() }
    )
}

// Add similar functions to the other nested classes
fun Transaction.Datastore.toUiModel(): CounterUi.TransactionUi.DatastoreUi {
    return CounterUi.TransactionUi.DatastoreUi(
        name = name.toString(),
        commit = commit,
        totalTime = totalTime,
        serviceTime = serviceTime,
        validationTime = validationTime,
        globalLockTime = globalLockTime,
        globalLockWaitTime = globalLockWaitTime,
        aborts = aborts,
        conflicts = conflicts,
        retries = retries
    )
}

fun ServiceConflicts.toUiModel(): CounterUi.ServiceConflictsUi {
    return CounterUi.ServiceConflictsUi(
        serviceType = serviceType.map { it?.toUiModel() }
    )
}

fun ServiceConflicts.ServiceType.toUiModel(): CounterUi.ServiceConflictsUi.ServiceTypeUi {
    return CounterUi.ServiceConflictsUi.ServiceTypeUi(
        name = name,
        conflicts = conflicts
    )
}


fun CdbCounters.toUiModel(): CounterUi.CdbUi {
    return CounterUi.CdbUi(
        compactions = compactions,
        compaction = compaction?.toUiModel(),
        bootTime = bootTime,
        phase0Time = phase0Time,
        phase1Time = phase1Time,
        phase2Time = phase2Time
    )
}

fun CdbCounters.Compaction.toUiModel(): CounterUi.CdbUi.CompactionUi {
    return CounterUi.CdbUi.CompactionUi(
        ACdb = ACdb,
        OCdb = OCdb,
        SCdb = SCdb,
        total = total
    )
}

fun DeviceCounters.toUiModel(): CounterUi.DeviceUi {
    return CounterUi.DeviceUi(
        connect = connect,
        connectFailed = connectFailed,
        syncFrom = syncFrom,
        syncTo = syncTo,
        outOfSync = outOfSync
    )
}

fun SessionCounters.toUiModel(): CounterUi.SessionUi {
    return CounterUi.SessionUi(
        total = total,
        netconfTotal = netconfTotal,
        restconfTotal = restconfTotal,
        jsonrpcTotal = jsonrpcTotal,
        snmpTotal = snmpTotal,
        cliTotal = cliTotal
    )
}
