package se.kruskakli.nso.data.devices

import com.squareup.moshi.Json

data class CommitQueue(
    @Json(name = "queue-length") val queueLength: Int
)