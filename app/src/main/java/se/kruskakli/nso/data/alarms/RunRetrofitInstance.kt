package se.kruskakli.nso.data.alarms

import kotlinx.coroutines.runBlocking
import se.kruskakli.nso.data.RetrofitInstance

fun main() {
    runBlocking {
        val api = RetrofitInstance.getApi("http://192.168.1.231:9080/restconf/data/", "admin", "admin")
        val alarmList = api.getNsoAlarmList()
        println(alarmList)
    }
}