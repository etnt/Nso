package se.kruskakli.nso.data.releasenote
/*
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ReleaseNoteTest {
    private val moshi = Moshi.Builder()
        .add(TextPieceAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    private val jsonAdapter = moshi.adapter<List<ReleaseNote>>(Types.newParameterizedType(List::class.java, ReleaseNote::class.java))

    @Test
    fun testJsonParsing() {
        val jsonString = """
            [
                {
                    "version": "1.0.0",
                    "date": "2022-01-01",
                    "textPieces": [
                        {
                            "type": "Paragraph",
                            "text": "This is a paragraph."
                        },
                        {
                            "type": "BulletList",
                            "items": ["Item 1", "Item 2", "Item 3"]
                        }
                    ]
                }
            ]
        """.trimIndent()

        val releaseNotes = jsonAdapter.fromJson(jsonString)

        assertEquals(1, releaseNotes?.size)
        assertEquals("1.0.0", releaseNotes?.get(0)?.version)
        assertEquals("2022-01-01", releaseNotes?.get(0)?.date)
        assertEquals(2, releaseNotes?.get(0)?.textPieces?.size)
        assertEquals("This is a paragraph.", (releaseNotes?.get(0)?.textPieces?.get(0) as TextPiece.Paragraph).text)
        assertEquals(listOf("Item 1", "Item 2", "Item 3"), (releaseNotes?.get(0)?.textPieces?.get(1) as TextPiece.BulletList).items)
    }
}
*/