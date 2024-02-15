package se.kruskakli.nso.data.syscounters

import com.squareup.moshi.Json

/*
 convert the selected Yang code into a Kotlin data class where
 the container name becomes the name of the data class and the
 leaf members become members of the data class. Also, create a
 companion singleton which holds each descriptions string as a
 constant value. Also add moshi.Json annotation in front of
 those members where the original name differs from the given
 Kotlin name.
 */
data class CdbCounters(
    val compactions: Long?,
    @Json(name = "compaction") val compaction: Compaction?,
    @Json(name = "boot-time") val bootTime: String?,
    @Json(name = "phase0-time") val phase0Time: String?,
    @Json(name = "phase1-time") val phase1Time: String?,
    @Json(name = "phase2-time") val phase2Time: String?
) {
    data class Compaction(
        @Json(name = "A-cdb") val ACdb: Long?,
        @Json(name = "O-cdb") val OCdb: Long?,
        @Json(name = "S-cdb") val SCdb: Long?,
        val total: Long?
    )
}