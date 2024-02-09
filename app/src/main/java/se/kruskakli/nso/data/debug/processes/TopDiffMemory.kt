package se.kruskakli.nso.data.debug.processes

data class TopDiffMemory(
    val ccall: String,
    val icall: String,
    val memory: String,
    val pid: String
)