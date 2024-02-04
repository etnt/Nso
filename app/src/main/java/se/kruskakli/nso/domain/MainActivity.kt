package se.kruskakli.nso.domain

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import se.kruskakli.nso.presentation.HomeScreen
import se.kruskakli.nso.ui.theme.NsoTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import se.kruskakli.nso.data.RetrofitInstance
import se.kruskakli.nso.data.devices.toDeviceUi
import se.kruskakli.nso.data.packages.toPackageUi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("MainActivity", "HERE")
            var name by remember { mutableStateOf("Blueberry") }
            var ipAddress by remember { mutableStateOf("10.147.40.166") }
            var port by remember { mutableStateOf("8080")}
            var applySettings: (String,String,String) -> Unit = fun(newName, newIp, newPort) {
                name = newName
                ipAddress = newIp
                port = newPort
            }

            var nsoPackages by remember { mutableStateOf(listOf<PackageUi>()) }
            var getNsoPackages = fun() {
                GlobalScope.launch(Dispatchers.IO) {
                    val api = RetrofitInstance.getApi(
                        "http://${ipAddress}:${port}/restconf/data/",
                        "admin",
                        "admin"
                    )
                    val response = api.getPackages()
                    if (response.tailfNcsPackages != null) {

                        withContext(Dispatchers.Main) {
                            val newPackages = mutableListOf<PackageUi>()
                            response.tailfNcsPackages.nsoPackages.forEach() {
                                Log.d("MainActivity", "BODY: ${it}")
                                val p = it.toPackageUi()
                                newPackages.add(p)
                            }
                            nsoPackages = newPackages
                        }
                    }
                }
            }

            var nsoDevices by remember { mutableStateOf(listOf<DeviceUi>()) }
            var getNsoDevices = fun() {
                GlobalScope.launch(Dispatchers.IO) {
                    val api = RetrofitInstance.getApi(
                        "http://${ipAddress}:${port}/restconf/data/",
                        "admin",
                        "admin"
                    )
                    val response = api.getNsoDevices()
                    if (response.nsoDevices != null) {

                        withContext(Dispatchers.Main) {
                            val newDevices = mutableListOf<DeviceUi>()
                            response.nsoDevices.devices.forEach() {
                                Log.d("MainActivity", "BODY: ${it}")
                                val p = it.toDeviceUi()
                                newDevices.add(p)
                            }
                            nsoDevices = newDevices
                        }
                    }
                }
            }

            NsoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Text("Hello, world!")
                    Log.d("MainActivity", "Before enter HomeScreen")
                    HomeScreen(name, ipAddress, port, applySettings, nsoPackages, getNsoPackages)


                    /*
                    MainScreen(
                        ip = ipFieldState,
                        port = portFieldState,
                        packages = nsopackages,
                        ipOnChange = { ipFieldState = it },
                        portOnChange = { portFieldState = it },
                        onAction = fun() {
                            GlobalScope.launch(Dispatchers.IO) {
                                val api = RetrofitInstance.getApi(
                                    "http://${ipFieldState}:${portFieldState}/restconf/data/",
                                    "admin",
                                    "admin"
                                )
                                val response = api.getPackages()
                                if (response.tailfNcsPackages != null) {

                                    withContext(Dispatchers.Main) {
                                        val newPackages = mutableListOf<PackageUi>()
                                        response.tailfNcsPackages.nsoPackages.forEach() {
                                            Log.d("MainActivity", "BODY: ${it}")
                                            val p = it.toPackageUi()
                                            newPackages.add(p)
                                        }
                                        nsopackages = newPackages
                                    }
                                }
                            }
                            //Log.d("MainActivity", "packages: ${nsopackages}")
                        }
                    )
                    */

                }
            }
        }
    }
}

