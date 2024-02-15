package se.kruskakli.nso.data

import retrofit2.http.GET
import se.kruskakli.nso.data.alarms.NsoAlarmList
import se.kruskakli.nso.data.debug.allocators.Allocators
import se.kruskakli.nso.data.debug.ets.Ets
import se.kruskakli.nso.data.debug.inet.Inet
import se.kruskakli.nso.data.debug.processes.NsoProcesses
import se.kruskakli.nso.data.packages.NsoPackages
import se.kruskakli.nso.data.devices.TailfNcsDevices
import se.kruskakli.nso.data.syscounters.NsoSysCounters


interface NsoApi {
    @GET("tailf-ncs%3Apackages")
    suspend fun getPackages(): NsoPackages

    @GET("tailf-ncs%3Adevices?depth=4")
    suspend fun getNsoDevices(): TailfNcsDevices

    @GET("tailf-ncs-alarms%3Aalarms/alarm-list")
    suspend fun getNsoAlarmList(): NsoAlarmList

    @GET("nso-dbg%3Anso-dbg/beam-state/inet")
    suspend fun getNsoInet(): Inet

    @GET("nso-dbg%3Anso-dbg/beam-state/ets-tables")
    suspend fun getNsoEts(): Ets

    @GET("nso-dbg%3Anso-dbg/beam-state/memory/stats/live/allocators")
    suspend fun getNsoAllocators(): Allocators

    @GET("nso-dbg%3Anso-dbg/beam-state/processes/all")
    suspend fun getNsoProcesses(): NsoProcesses

    @GET("tailf-ncs%3Ametric/sysadmin/counter")
    suspend fun getSysCounters(): NsoSysCounters

}