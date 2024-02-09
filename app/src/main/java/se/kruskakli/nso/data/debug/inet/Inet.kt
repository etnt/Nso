package se.kruskakli.nso.data.debug.inet

import com.squareup.moshi.Json

data class Inet(
    @Json(name = "nso-dbg:inet") val inet: NsoInet
)