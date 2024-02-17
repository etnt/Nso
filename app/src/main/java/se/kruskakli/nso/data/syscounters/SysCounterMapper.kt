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
        commit = commit.toString(),
        totalTime = totalTime,
        serviceTime = serviceTime,
        validationTime = validationTime,
        globalLockTime = globalLockTime,
        globalLockWaitTime = globalLockWaitTime,
        aborts = aborts?.toString(),
        conflicts = conflicts?.toString(),
        retries = retries?.toString()
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
        compactions = compactions?.let{it.toString()},
        compaction = compaction?.toUiModel(),
        bootTime = bootTime,
        phase0Time = phase0Time,
        phase1Time = phase1Time,
        phase2Time = phase2Time
    )
}

fun CdbCounters.Compaction.toUiModel(): SysCountersUi.CdbUi.CompactionUi {
    return SysCountersUi.CdbUi.CompactionUi(
        ACdb = ACdb?.let{it.toString()},
        OCdb = OCdb?.let{it.toString()},
        SCdb = SCdb?.let{it.toString()},
        total = total?.let{it.toString()}
    )
}

fun DeviceCounters.toUiModel(): SysCountersUi.DeviceUi {
    return SysCountersUi.DeviceUi(
        connect = connect?.let{it.toString()},
        connectFailed = connectFailed?.let{it.toString()},
        syncFrom = syncFrom?.let{it.toString()},
        syncTo = syncTo?.let{it.toString()},
        outOfSync = outOfSync?.let{it.toString()}
    )
}

fun SessionCounters.toUiModel(): SysCountersUi.SessionUi {
    return SysCountersUi.SessionUi(
        total = total?.let{it.toString()},
        netconfTotal = netconfTotal?.let{it.toString()},
        restconfTotal = restconfTotal?.let{it.toString()},
        jsonrpcTotal = jsonrpcTotal?.let{it.toString()},
        snmpTotal = snmpTotal?.let{it.toString()},
        cliTotal = cliTotal?.let{it.toString()}
    )
}
