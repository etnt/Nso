package se.kruskakli.nso.data.syscounters

import se.kruskakli.nso.domain.SysCountersUi

fun SysCounters.toUiModel(): SysCountersUi {
    return SysCountersUi(
        transaction = transaction.toUiModel(),
        serviceConflicts = serviceConflicts?.toUiModel(),
        cdb = cdb.toUiModel(),
        device = device.toUiModel(),
        session = session.toUiModel()
    )
}


// Add this function to the Transaction class
fun Transaction.toUiModel(): SysCountersUi.TransactionUi {
    return SysCountersUi.TransactionUi(
        datastore = datastore.map { it.toUiModel() }
    )
}

// Add similar functions to the other nested classes
fun Transaction.Datastore.toUiModel(): SysCountersUi.TransactionUi.DatastoreUi {
    return SysCountersUi.TransactionUi.DatastoreUi(
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

fun ServiceConflicts.toUiModel(): SysCountersUi.ServiceConflictsUi {
    return SysCountersUi.ServiceConflictsUi(
        serviceType = serviceType.map { it?.toUiModel() }
    )
}

fun ServiceConflicts.ServiceType.toUiModel(): SysCountersUi.ServiceConflictsUi.ServiceTypeUi {
    return SysCountersUi.ServiceConflictsUi.ServiceTypeUi(
        name = name,
        conflicts = conflicts
    )
}


fun CdbCounters.toUiModel(): SysCountersUi.CdbUi {
    return SysCountersUi.CdbUi(
        compactions = compactions,
        compaction = compaction?.toUiModel(),
        bootTime = bootTime,
        phase0Time = phase0Time,
        phase1Time = phase1Time,
        phase2Time = phase2Time
    )
}

fun CdbCounters.Compaction.toUiModel(): SysCountersUi.CdbUi.CompactionUi {
    return SysCountersUi.CdbUi.CompactionUi(
        ACdb = ACdb,
        OCdb = OCdb,
        SCdb = SCdb,
        total = total
    )
}

fun DeviceCounters.toUiModel(): SysCountersUi.DeviceUi {
    return SysCountersUi.DeviceUi(
        connect = connect,
        connectFailed = connectFailed,
        syncFrom = syncFrom,
        syncTo = syncTo,
        outOfSync = outOfSync
    )
}

fun SessionCounters.toUiModel(): SysCountersUi.SessionUi {
    return SysCountersUi.SessionUi(
        total = total,
        netconfTotal = netconfTotal,
        restconfTotal = restconfTotal,
        jsonrpcTotal = jsonrpcTotal,
        snmpTotal = snmpTotal,
        cliTotal = cliTotal
    )
}
