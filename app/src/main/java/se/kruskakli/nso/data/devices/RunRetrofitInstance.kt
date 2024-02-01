package se.kruskakli.nso.data.devices

import kotlinx.coroutines.runBlocking
import se.kruskakli.nso.data.devices.RetrofitInstance

fun main() {
    runBlocking {
        val api = RetrofitInstance.getApi("http://10.147.40.166:8080/restconf/data/", "admin", "admin")
        val devices = api.getNsoDevices()
        println(devices)
    }
}