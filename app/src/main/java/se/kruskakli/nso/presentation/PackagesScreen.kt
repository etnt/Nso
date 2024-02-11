package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import se.kruskakli.nso.domain.PackageUi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle


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
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Divider()
            LazyColumn {
                items(items = packages) {
                    Package(it)
                }
            }
        }
    }
}


@Composable
fun Package(
    p: PackageUi,
    modifier: Modifier = Modifier
) {
    var show by remember { mutableStateOf(false) }
    val toggleShow = { show = !show }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            PackagesHeadField(p, toggleShow)
            if (show) {
                val fields = listOf(
                    Field("Name", p.name),
                    Field("Version", p.packageVersion),
                    Field("Description", p.description),
                    Field("Directory", p.directory),
                    Field("NCS Min Version", p.ncsMinVersion.toString())
                )
                InsideCard(
                    header = "NSO Package:",
                    fields = fields,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
            Divider()
        }
    }
}


@Composable
fun PackagesHeadField(
    p: PackageUi,
    toggleShow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { toggleShow() })
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append("Package: ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(p.name)
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append("  (")
            }
            withStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append(p.packageVersion)
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append(")  - ")
            }
            withStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic
                )
            ) {
                append(p.description)
            }
        }
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp)
        )
    }
}