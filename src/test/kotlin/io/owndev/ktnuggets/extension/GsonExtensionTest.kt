package io.owndev.ktnuggets.extension

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class GsonExtensionTest {

    @Test
    fun `should deserialize a list`() {
        val encList = "[{'priceInMinor': 1000, 'currency': 'EUR'}]"
        val decList = Gson().fromJson<List<Price>>(encList)

        assertEquals(decList[0].priceInMinor, 1000)
        assertEquals(decList[0].currency, "EUR")
    }
}

data class Price(
    val priceInMinor: Int,
    val currency: String
)
