package se.kruskakli.nso.data.packages

import kotlinx.coroutines.runBlocking
import se.kruskakli.nso.data.RetrofitInstance

fun main() {
    runBlocking {
        val api = RetrofitInstance.getApi("http://10.147.40.166:8080/restconf/data/", "admin", "admin")
        val packages = api.getPackages()
        println(packages)
    }
}