package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

data class NsoSysCounters(
    @Json(name = "tailf-ncs:counter") val sysCounters: SysCounters
)