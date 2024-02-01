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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import se.kruskakli.nsopackages.data.RetrofitInstance
import se.kruskakli.nsopackages.data.toPackageUi
import se.kruskakli.nsopackages.presentation.MainScreen
import se.kruskakli.nsopackages.ui.theme.NsoPackagesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var ipFieldState by remember {
                mutableStateOf("10.147.40.166")
            }
            var portFieldState by remember {
                mutableStateOf("8080")
            }
            var nsopackages by remember { mutableStateOf(listOf<PackageUi>()) }

            NsoPackagesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                }
            }
        }
    }
}

