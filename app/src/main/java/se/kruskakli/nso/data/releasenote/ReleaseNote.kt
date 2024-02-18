package se.kruskakli.nso.data.releasenote

import androidx.compose.ui.text.AnnotatedString
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

/*
Example JSON of a list of release notes:
[
 {
   "version": "0.1.0",
   "date": "2021-10-01",
   "textPieces": [
     {
       "type": "Paragraph",
       "text": "This is a release note for the new version of the app."
     },
     {
       "type": "BulletList",
       "items": [
         "New feature: Dark mode",
         "Bug fix: Crash on startup"
       ]
     }
   ]
 }
]

*/

@JsonClass(generateAdapter = true)
data class ReleaseNote(
    val version: String,
    val date: String,
    val textPieces: List<TextPiece>
)

@JsonClass(generateAdapter = true)
sealed class TextPiece {
    @JsonClass(generateAdapter = true)
    data class Paragraph(val text: String) : TextPiece()

    @JsonClass(generateAdapter = true)
    data class BulletList(val items: List<String>) : TextPiece()
}

class TextPieceAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): TextPiece {
        var type: String? = null
        var text: String? = null
        var items: List<String>? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "type" -> type = reader.nextString()
                "text" -> text = reader.nextString()
                "items" -> items = reader.readJsonValue() as? List<String>
            }
        }
        reader.endObject()

        return when (type) {
            "Paragraph" -> TextPiece.Paragraph(text ?: "")
            "BulletList" -> TextPiece.BulletList(items ?: listOf())
            else -> throw IllegalArgumentException("Unknown type of TextPiece")
        }
    }
    @ToJson
    fun toJson(writer: JsonWriter, textPiece: TextPiece) {
        writer.beginObject()
        when (textPiece) {
            is TextPiece.Paragraph -> {
                writer.name("type").value("Paragraph")
                writer.name("text").value(textPiece.text)
            }
            is TextPiece.BulletList -> {
                writer.name("type").value("BulletList")
                writer.name("items").beginArray()
                for (item in textPiece.items) {
                    writer.value(item)
                }
                writer.endArray()
            }
        }
        writer.endObject()
    }
}