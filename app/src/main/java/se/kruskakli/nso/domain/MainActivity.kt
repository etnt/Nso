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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var nsopackages by remember { mutableStateOf(listOf<PackageUi>()) }

            var name by remember { mutableStateOf("Blueberry") }
            var ipAddress by remember { mutableStateOf("10.147.40.166") }
            var port by remember { mutableStateOf("8080")}

            NsoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        name,
                        ipAddress,
                        port,
                        { _name, _ip, _port ->
                            name = _name
                            ipAddress = _ip
                            port = _port
                        }
                    )
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

