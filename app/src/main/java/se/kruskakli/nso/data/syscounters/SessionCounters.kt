package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class SessionCounters(
    val total: Long?,
    @Json(name = "netconf-total") val netconfTotal: Long?,
    @Json(name = "restconf-total") val restconfTotal: Long?,
    @Json(name = "jsonrpc-total") val jsonrpcTotal: Long?,
    @Json(name = "snmp-total") val snmpTotal: Long?,
    @Json(name = "cli-total") val cliTotal: Long?
) {
    companion object {
        const val TOTAL_DESCRIPTION = "Total number of northbound sessions completed."
        const val NETCONF_TOTAL_DESCRIPTION = "Total number of NETCONF sessions completed."
        const val RESTCONF_TOTAL_DESCRIPTION = "Total number of RESTCONF sessions completed."
        const val JSONRPC_TOTAL_DESCRIPTION = "Total number of JSONRPC sessions completed."
        const val SNMP_TOTAL_DESCRIPTION = "Total number of SNMP sessions completed."
        const val CLI_TOTAL_DESCRIPTION = "Total number of CLI sessions completed."
    }
}
