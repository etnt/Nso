package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


enum class TabPage {
    Home, Settings, Packages, Devices, Error, Alarms, About
}

@Composable
fun Divider() {
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(Color.LightGray)
    )
}

data class Field(
    val label: String,
    val value: String
)

@Composable
fun FieldComponent(
    field: Field,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${field.label}:",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = field.value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}