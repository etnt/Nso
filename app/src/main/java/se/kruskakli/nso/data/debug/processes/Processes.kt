package se.kruskakli.nso.data.debug.processes

import com.squareup.moshi.Json

data class Processes(
    @Json(name = "nso-dbg:processes") val nsoProcesses: NsoProcesses
)