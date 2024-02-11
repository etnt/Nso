package se.kruskakli.nso.data.debug.allocators

import com.squareup.moshi.Json

data class NsoAllocators(
    val allocator: List<Allocator>,
    @Json(name = "blocks-size") val blocksSize: String,
    @Json(name = "carriers-size") val carriersSize: String,
    val utilization: String,
    val mbcs: SizeInfo,
    val sbcs: SizeInfo
)