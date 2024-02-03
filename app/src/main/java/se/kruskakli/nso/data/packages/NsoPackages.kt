package se.kruskakli.nso.data.packages

import com.squareup.moshi.Json

data class NsoPackages(
    @Json(name = "tailf-ncs:packages") val tailfNcsPackages: TailfNcsPackages
)