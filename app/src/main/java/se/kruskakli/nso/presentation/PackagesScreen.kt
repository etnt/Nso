package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import se.kruskakli.nso.domain.PackageUi


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
                FieldComponent(Field("Package", name))
                FieldComponent(Field("Version", version))
                FieldComponent(Field("Description", description))
                FieldComponent(Field("Directory", directory))
                FieldComponent(Field("NCS Min Version", ncsMinVersion.toString()))
            }
        }
    }
}

