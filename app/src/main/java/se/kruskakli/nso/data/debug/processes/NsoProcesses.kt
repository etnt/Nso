package se.kruskakli.nso.data.debug.processes

import com.squareup.moshi.Json

data class NsoProcesses(
    @Json(name = "top-diff-memory") val topDiffMemory: List<TopDiffMemory>,
    @Json(name = "top-diff-reductions") val topDiffReductions: List<TopDiffReduction>,
    @Json(name = "top-shared-binaries") val topSharedBinaries: List<TopSharedBinary>
)