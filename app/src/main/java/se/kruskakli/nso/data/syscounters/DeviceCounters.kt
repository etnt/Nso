package se.kruskakli.nso.data.syscounters

/*
 * Collection of device oriented counters.
 */
data class DeviceCounters(
    /*
     * Total number of device connects.
     */
    val connect: Long?,

    /*
     * Total number of failed device connects.
     */
    val connectFailed: Long?,

    /*
     * Total number of sync-from invocations.
     */
    val syncFrom: Long?,

    /*
     * Total number of sync-to invocations.
     */
    val syncTo: Long?,

    /*
     * Total number of out of sync detections.
     */
    val outOfSync: Long?
) {
    // You can access these constants anywhere in your code using
    // Device.Comments.CLASS_COMMENT, Device.Comments.CONNECT_COMMENT, etc.
    companion object Comments {
        const val CLASS_COMMENT = "Collection of device oriented counters."
        const val CONNECT_COMMENT = "Total number of device connects."
        const val CONNECT_FAILED_COMMENT = "Total number of failed device connects."
        const val SYNC_FROM_COMMENT = "Total number of sync-from invocations."
        const val SYNC_TO_COMMENT = "Total number of sync-to invocations."
        const val OUT_OF_SYNC_COMMENT = "Total number of out of sync detections."
    }
}
