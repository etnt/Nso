package se.kruskakli.nso.data.debug.processes

import com.squareup.moshi.Json

data class TopSharedBinary(
    val ccall: String,
    val icall: String,
    val pid: String,
    @Json(name = "shared-binaries") val sharedBinaries: String
)