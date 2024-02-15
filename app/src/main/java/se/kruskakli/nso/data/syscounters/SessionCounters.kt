package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class SessionCounters(
    val total: Long?,
    @Json(name = "netconf-total") val netconfTotal: Long?,
    @Json(name = "restconf-total") val restconfTotal: Long?,
    @Json(name = "jsonrpc-total") val jsonrpcTotal: Long?,
    @Json(name = "snmp-total") val snmpTotal: Long?,
    @Json(name = "cli-total") val cliTotal: Long?
)
