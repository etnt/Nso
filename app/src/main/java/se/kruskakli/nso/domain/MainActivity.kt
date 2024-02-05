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
import androidx.lifecycle.viewmodel.compose.viewModel
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

            NsoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Text("Hello, world!")
                    Log.d("MainActivity", "Before enter HomeScreen")
                    val viewModel: MainViewModel = viewModel()
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

