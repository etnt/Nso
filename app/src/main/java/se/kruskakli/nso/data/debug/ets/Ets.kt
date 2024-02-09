package se.kruskakli.nso.data.debug.ets

import com.squareup.moshi.Json

data class Ets(
    @Json(name = "nso-dbg:ets-tables") val nsoEtsTables: NsoEtsTables
)