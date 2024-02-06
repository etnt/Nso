package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
    nsoPackages: List<PackageUi>,
    modifier: Modifier = Modifier
) {
    Packages(nsoPackages)
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
            .padding(1.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${label}:",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

