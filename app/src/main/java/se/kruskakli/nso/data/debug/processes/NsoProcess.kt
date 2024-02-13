package se.kruskakli.nso.data.debug.processes

import com.squareup.moshi.Json

data class NsoProcess(
    val ccall: String,
    val icall: String,
    val memory: String,
    val msgs: String,
    val name: String,
    val pid: String,
    val reds: String,
    @Json(name = "shared-binaries") val sharedBinaries: String?
)