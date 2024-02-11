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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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


data class Field(
    val label: String,
    val value: String?
)

@Composable
fun FieldComponent(
    field: Field,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 4.dp, end = 4.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${field.label}:",
            color = textColor,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = field.value ?: "",
            color = textColor,
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
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    color: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                text = header,
                color = textColor,
                style = MaterialTheme.typography.titleSmall
            )
            fields.forEach { field ->
                FieldComponent(
                    field = field,
                    textColor = textColor
                )
            }
        }
    }
}

/*
   Prompt:
   The selected code consists of a Composable that will create an OutlinedCard.
   Create a function that can create a similar composable: OutlinedCards ,which
   takes a list of fields but also a list of OutlinedCard that should be nested
   within.
*/
@Composable
fun OutlinedCards(
    header: String,
    fields: List<Field>,
    cards: List<@Composable () -> Unit>,
    color: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 2.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                text = header,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall
            )
            fields.forEach { field ->
                FieldComponent(field)
            }
            cards.forEach { card ->
                card()
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

