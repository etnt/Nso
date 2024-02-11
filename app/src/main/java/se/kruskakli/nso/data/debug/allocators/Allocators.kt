package se.kruskakli.nso.data.debug.allocators

import com.squareup.moshi.Json

data class Allocators(
    @Json(name = "nso-dbg:allocators") val nsoAllocators: NsoAllocators
)