package se.kruskakli.nso.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import se.kruskakli.nso.domain.MainIntent
import se.kruskakli.nso.domain.MainViewModel
import se.kruskakli.nso.domain.SettingsData


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
    val user by viewModel.user.collectAsState()
    val passwd by viewModel.passwd.collectAsState()
    val refresh by viewModel.refresh.collectAsState()

    var newName by remember { mutableStateOf(name) }
    var newIp by remember { mutableStateOf(ip) }
    var newPort by remember { mutableStateOf(port) }
    var newUser by remember { mutableStateOf(user) }
    var newPasswd by remember { mutableStateOf(passwd) }
    var shouldRefresh by remember { mutableStateOf(refresh) }

    var isModified by remember { mutableStateOf(false) }

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
                            val settingsData = SettingsData(newName, newIp, newPort, newUser, newPasswd, shouldRefresh)
                            viewModel.handleIntent(MainIntent.SaveSettings(settingsData))
                        },
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
            }
            CustomRow(
                value1 = newIp,
                text1 = "IP Address:",
                onValueChange1 = {
                    isModified = true
                    newIp = it
                },
                value2 = newPort,
                text2 = "Port:",
                onValueChange2 = {
                    isModified = true
                    newPort = it
                }
            )
            CustomRow(
                value1 = newUser,
                text1 = "User:",
                onValueChange1 = {
                    isModified = true
                    newUser = it
                },
                value2 = newPasswd,
                text2 = "Password:",
                onValueChange2 = {
                    isModified = true
                    newPasswd = it
                }
            )
        }
    }
}

@Composable
fun CustomRow(
    value1: String,
    text1: String,
    onValueChange1: (String) -> Unit,
    value2: String,
    text2: String,
    onValueChange2: (String) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        CustomTextField(
            value = value1,
            text = text1,
            onValueChange = onValueChange1,
            modifier = Modifier.weight(1f)
        )
        CustomTextField(
            value = value2,
            text = text2,
            onValueChange = onValueChange2,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        modifier = modifier,
        textStyle = MaterialTheme.typography.titleMedium,
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall
            )
        },
        onValueChange = onValueChange,
        // To remove the ugly underline
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}