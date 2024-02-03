package se.kruskakli.nso.data.packages

import com.squareup.moshi.Json


data class Package(
    val name: String,
    @Json(name = "package-version") val packageVersion: String,
    val description: String,
    val directory: String,
    @Json(name = "ncs-min-version") val ncsMinVersion: List<String>

    /*
    @Transient val component: List<Component>? = null,
    @Transient val operStatus: OperStatus? = null,
    @Transient val templateLoadingMode: String? = null,
    @Transient val templates: List<String>? = null

    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("component") val component: List<Component>,

    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("oper-status") val operStatus: OperStatus,

    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("template-loading-mode") val templateLoadingMode: String,

    @Expose(deserialize = false) // deserialize is this filed is not required
    @SerializedName("templates") val templates: List<String>
    */
)