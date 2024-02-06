package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import se.kruskakli.nso.domain.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ipPortSettings(viewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ipPortSettings(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val name by viewModel.name.collectAsState()
    val ip by viewModel.ipAddress.collectAsState()
    val port by viewModel.port.collectAsState()

    var newName by remember { mutableStateOf(name) }
    var newIp by remember { mutableStateOf(ip) }
    var newPort by remember { mutableStateOf(port) }
    var saveColor by remember { mutableStateOf(Color.Black) }

    Card(
        modifier = Modifier
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        var isModified by remember { mutableStateOf(false) }
        var shouldRefresh by remember { mutableStateOf(false) }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = newName,
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = {
                        Text(
                            text = "Name:",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onValueChange = { newName = it },
                    // To remove the ugly underline
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                val refreshText = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 16.sp)) {
                        append("Refresh")
                    }
                }
                if (!shouldRefresh) {
                    ClickableText(
                        text = refreshText,
                        onClick = {
                            shouldRefresh = true
                            isModified = true
                        },
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Red),
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }

                val saveText = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 16.sp)) {
                        append("Save")
                    }
                }
                if (isModified) {
                    ClickableText(
                        text = saveText,
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Blue),
                        onClick = {
                            isModified = false
                            viewModel.applySettings(newName, newIp, newPort)
                            if (shouldRefresh) {
                                viewModel.resetNsoPackages()
                                viewModel.resetNsoDevices()
                            }
                        },
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = newIp,
                    modifier = Modifier.weight(1f),
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = {
                        Text(
                            text = "IP Address:",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onValueChange = {
                        isModified = true
                        newIp = it
                    },
                    // To remove the ugly underline
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                TextField(
                    value = newPort,
                    modifier = Modifier.weight(1f),
                    textStyle = MaterialTheme.typography.titleMedium,
                    label = {
                        Text(
                            text = "Port:",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    onValueChange = {
                        isModified = true
                        newPort = it
                    },
                    // To remove the ugly underline
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        }

    }
}
