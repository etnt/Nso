package se.kruskakli.nso.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle


@Composable
fun Divider() {
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
    )
}



data class Field(
    val label: String,
    val value: String?
)

data class FieldWithHelp(
    val label: String,
    val value: String,
    val help: String = ""
)





@Composable
fun FieldComponent(
    field: Field,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
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

@Composable
fun FieldComponentWithHelp(
    field: FieldWithHelp,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    var show by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 4.dp, end = 4.dp, bottom = 0.dp)
            .clickable { show = !show }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
        if (show) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = field.help ?: "",
                fontStyle = FontStyle.Italic,
                color = textColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/*
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun FieldComponentPreview() {
    FieldComponent(Field("Label", "Value"))
}
*/


@Composable
fun InsideCard(
    header: String,
    fields: List<Field>,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    color: Color = MaterialTheme.colorScheme.surface,
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

@Composable
fun InsideCardWithHelp(
    header: String,
    fields: List<FieldWithHelp?>,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    color: Color = MaterialTheme.colorScheme.surface,
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
                field?.let {
                    FieldComponentWithHelp(
                        field = it,
                        textColor = textColor
                    )
                }
            }
        }
    }
}

// Build a text string with an optional annotation
@Composable
fun annotatedText(
    text: String,
    annotation: String? = null
) : AnnotatedString
{
    val annotatedText = buildAnnotatedString {
        append(text)
        if (annotation != null) {
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    fontStyle = FontStyle.Italic,
                )
            ) {
                append(annotation)
            }
        }
    }
    return annotatedText
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
    annotatedHeader: AnnotatedString? = null,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    color: Color = MaterialTheme.colorScheme.surface,
    show: Boolean = true,
    modifier: Modifier = Modifier
) {
    var showAll by remember { mutableStateOf(show) }

    OutlinedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 2.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showAll = !showAll }
                .background(color)
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            // An optional annotated header takes precedence over the header!
            val text = annotatedHeader ?: AnnotatedString(header)
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.titleSmall
            )
            if (showAll) {
                fields.forEach { field ->
                    FieldComponent(field, textColor)
                }
                cards.forEach { card ->
                    card()
                }
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

/*
   Prompt:
   In my Jetpack Compose application I want to be able to create
   simple text pieces to be displayed. I should be able to express:
   paragraphs and bullet lists ,where the text should be of type
   AnnotatedString. Create a composable for this with a data class
   to represent the text.
 */

sealed class TextPiece {
    data class Paragraph(val text: AnnotatedString) : TextPiece()
    data class BulletList(val items: List<AnnotatedString>) : TextPiece()
}

@Composable
fun TextDisplay(textPieces: List<TextPiece>) {
    Column {
        textPieces.forEach { textPiece ->
            when (textPiece) {
                is TextPiece.Paragraph -> {
                    Text(text = textPiece.text)
                }
                is TextPiece.BulletList -> {
                    textPiece.items.forEach { item ->
                        Text(text = "\u2022 ${item}")
                    }
                }
            }
        }
    }
}