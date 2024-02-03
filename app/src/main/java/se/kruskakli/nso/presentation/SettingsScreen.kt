package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    name: String,
    ip: String,
    port: String,
    onAction: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ipPortSettings(name, ip, port, onAction)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ipPortSettings(
    name: String,
    ip: String,
    port: String,
    onAction: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newName by remember { mutableStateOf(name) }
    var newIp by remember { mutableStateOf(ip) }
    var newPort by remember { mutableStateOf(port) }

    Card(
        modifier = Modifier
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            TextField(
                value = newName,
                label = { Text(text = "Name:") },
                onValueChange = { newName = it },
                modifier = Modifier
                    .fillMaxWidth(),
                // To remove the ugly underline
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = newIp,
                    label = { Text(text = "IP Address:") },
                    onValueChange = { newIp = it },
                    //modifier = Modifier
                    //    .fillMaxWidth(),
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
                    label = { Text(text = "Port:") },
                    onValueChange = { newPort = it },
                    //modifier = Modifier
                    //    .fillMaxWidth(),
                    // To remove the ugly underline
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            Button(onClick = { onAction(newName, newIp, newPort) }) {
                Text("Apply Changes")
            }
        }

    }
}
