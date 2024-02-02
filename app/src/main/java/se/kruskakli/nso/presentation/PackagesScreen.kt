package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import se.kruskakli.nso.domain.PackageUi
import se.kruskakli.nso.ui.theme.NsoTheme

@Composable
fun PackagesScreen(
    ip: String,
    port: String,
    modifier: Modifier = Modifier
) {
    //var packages by remember { mutableStateOf(listOf<PackageUi>()) }
/*
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

 */
}

@Composable
fun Packages(
    packages: List<PackageUi>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(items = packages) {
            Log.d("NsoPackagesAScreen", "it: ${it}")
            Package(
                name = it.name,
                version = it.packageVersion,
                description = it.description,
                directory = it.directory,
                ncsMinVersion = it.ncsMinVersion
            )
        }
        /*
        Package(
            name = "gned",
            version = "1.0",
            description = "Need state test",
            directory = "./state/packages-in-use/1/gned",
            ncsMinVersion = listOf("2.0")
        )
        Package(
            name = "gserv",
            version = "1.0",
            description = "Skeleton for a template-based service",
            directory = "./state/packages-in-use/1/gserv",
            ncsMinVersion = listOf("3.0")
        )
        */

    }
}


@Composable
fun Package(
    name: String,
    version: String,
    description: String,
    directory: String,
    ncsMinVersion: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            PackageField(label = "Package", value = name)
            PackageField(label = "Version", value = version)
            PackageField(label = "Description", value = description)
            PackageField(label = "Directory", value = directory)
            PackageField(label = "NCS Min Version", value = ncsMinVersion.toString())
        }
    }
}

@Composable
fun PackageField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${label}:",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

