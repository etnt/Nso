package se.kruskakli.nso.data.syscounters

data class SysCounters(
    val transaction: Transaction,
    val serviceConflicts: ServiceConflicts,
    val cdb: CdbCounters,
    val device: DeviceCounters,
    val session: SessionCounters
)