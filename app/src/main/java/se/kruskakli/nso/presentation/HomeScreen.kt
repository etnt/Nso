package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.kruskakli.nso.domain.PackageUi
import se.kruskakli.nso.domain.DeviceUi
import se.kruskakli.nso.domain.MainViewModel
import se.kruskakli.topbarexample.ui.RememberDevices
import se.kruskakli.topbarexample.ui.RememberPackages

/*
In this code, I've replaced the parameters of HomeScreen with a single
viewModel parameter. I've also replaced the calls to onSettingsChange,
setRefresh, getNsoPackages, and getNsoDevices with calls to the
corresponding methods on viewModel.

I've used collectAsState() to observe the StateFlows in the ViewModel.
This collects the latest value from the StateFlow and provides it as a
State that can be used in your Composable.

Finally, I've passed a lambda to SettingsScreen that calls viewModel.applySettings().
This allows SettingsScreen to update the settings in the ViewModel.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    var page by remember { mutableStateOf(TabPage.Home) }
    Log.d("MainActivity", "HomeScreen: ${page}")
    Scaffold(
        topBar = { myTopBar() { newPage -> page = newPage } },
    ) { padding ->
        Log.d("MainActivity", "BODY: ${page}")
        Box(modifier = Modifier
            .padding(
                top = padding.calculateTopPadding(),
                start = padding.calculateLeftPadding(LayoutDirection.Ltr),
                end = padding.calculateEndPadding(LayoutDirection.Ltr)
            )
        ) {
            Divider()
            when (page) {
                TabPage.Settings -> {
                    // By enter the SettingsScreen, we want to refresh the data.
                    viewModel.setRefresh(true)
                    val name by viewModel.name.collectAsState()
                    val ipAddress by viewModel.ipAddress.collectAsState()
                    val port by viewModel.port.collectAsState()
                    SettingsScreen(viewModel)
                }
                TabPage.Packages -> {
                    val nsoPackages by viewModel.nsoPackages.collectAsState()
                    val refresh by viewModel.refresh.collectAsState()
                    Log.d("MainActivity", "HomeScreen, before PACKAGES: ${nsoPackages}")
                    if (refresh || nsoPackages.isEmpty()) {
                        viewModel.getNsoPackages()
                        viewModel.setRefresh(false)
                    }
                    Log.d("MainActivity", "HomeScreen, after PACKAGES: ${nsoPackages}")
                    PackagesScreen(nsoPackages)
                }
                TabPage.Devices -> {
                    val nsoDevices by viewModel.nsoDevices.collectAsState()
                    val refresh by viewModel.refresh.collectAsState()
                    if (refresh || nsoDevices.isEmpty()) {
                        viewModel.getNsoDevices()
                        viewModel.setRefresh(false)
                    }
                    DevicesScreen(nsoDevices)
                }
                TabPage.Home -> {
                    WelcomePage()
                }
            }
        }
    }
}

@Composable
fun WelcomePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to NSO Mobile!",
            modifier = Modifier.padding(bottom = 200.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

@Composable
fun myTopBar(setScreen: (TabPage) -> Unit) {
    var text by remember { mutableStateOf("Home") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { setScreen(TabPage.Settings) }) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
        IconButton(
            modifier = Modifier.padding(bottom = 4.dp),
            onClick = { setScreen(TabPage.Packages) }
        ) {
            Icon(
                RememberPackages(),
                contentDescription = "Packages",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
        IconButton(
            modifier = Modifier.padding(bottom = 4.dp),
            onClick = { setScreen(TabPage.Devices) }
        ) {
            Icon(
                RememberDevices(),
                contentDescription = "Devices",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen("Blueberry", "10.0.0.1", "8888", { _, _, _ -> })
}
*/