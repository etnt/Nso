package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class TabPage {
    Home, Settings, Packages, Devices, Error, Alarms, About,
    Debug, Processes, Listeners, EtsTables
}

@Composable
fun Divider() {
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(Color.LightGray)
    )
}

@Composable
fun DeviceHeadField(
    label: String,
    value: String,
    toggleShow: () -> Unit,
    modifier: Modifier = Modifier
) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 12.sp)) {
            append(value)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${label}:",
            style = MaterialTheme.typography.titleSmall
        )
        ClickableText(
            text = text,
            onClick = { toggleShow() },
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Blue),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun DeviceHeadFieldPreview() {
    DeviceHeadField("Label", "Value", {})
}


data class Field(
    val label: String,
    val value: String?
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
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${field.label}:",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = field.value ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun FieldComponentPreview() {
    FieldComponent(Field("Label", "Value"))
}

@Composable
fun InsideCard(
    header: String,
    fields: List<Field>,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
        ) {
            Text(
                text = header,
                style = MaterialTheme.typography.titleSmall
            )
            fields.forEach { field ->
                FieldComponent(field)
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun InsideCardPreview() {
    InsideCard(
        header = "Header",
        fields = listOf(
            Field("Label1", "Value1"),
            Field("Label2", "Value2"),
            Field("Label3", "Value3")
        )
    )
}

@Composable
fun LoadingState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp),
            color = Color.Blue
        )
    }
}

