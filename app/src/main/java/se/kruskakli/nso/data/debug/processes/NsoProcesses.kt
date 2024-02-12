package se.kruskakli.nso.data.debug.processes

import com.squareup.moshi.Json

data class NsoProcesses(
    @Json(name = "nso-dbg:all") val nsoAllProcesses: List<NsoProcess>
)