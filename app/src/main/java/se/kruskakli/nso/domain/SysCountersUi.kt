package se.kruskakli.nso.domain


data class CounterUi(
    val transaction: TransactionUi,
    val serviceConflicts: ServiceConflictsUi,
    val cdb: CdbUi,
    val device: DeviceUi,
    val session: SessionUi
) {
    data class TransactionUi(
        val datastore: List<DatastoreUi>
    ) {
        data class DatastoreUi(
            val name: String,
            val commit: Long?,
            val totalTime: String?,
            val serviceTime: String?,
            val validationTime: String?,
            val globalLockTime: String?,
            val globalLockWaitTime: String?,
            val aborts: Long?,
            val conflicts: Long?,
            val retries: Long?
        )
    }

    data class ServiceConflictsUi(
        val serviceType: List<ServiceTypeUi?>
    ) {
        data class ServiceTypeUi(
            val name: String,
            val conflicts: Long
        )
    }

    data class CdbUi(
        val compactions: Long?,
        val compaction: CompactionUi?,
        val bootTime: String?,
        val phase0Time: String?,
        val phase1Time: String?,
        val phase2Time: String?
    ) {
        data class CompactionUi(
            val ACdb: Long?,
            val OCdb: Long?,
            val SCdb: Long?,
            val total: Long?
        )
    }

    data class DeviceUi(
        val connect: Long?,
        val connectFailed: Long?,
        val syncFrom: Long?,
        val syncTo: Long?,
        val outOfSync: Long?
    )

    data class SessionUi(
        val total: Long?,
        val netconfTotal: Long?,
        val restconfTotal: Long?,
        val jsonrpcTotal: Long?,
        val snmpTotal: Long?,
        val cliTotal: Long?
    )
}

